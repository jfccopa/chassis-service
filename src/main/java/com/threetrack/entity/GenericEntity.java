package com.threetrack.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.threetrack.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class GenericEntity {

	@JsonIgnore
	@Column(name = "t_create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@JsonIgnore
	@Column(name = "i_create_id")
	private Integer createId;

	@JsonIgnore
	@Column(name = "t_update_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@JsonIgnore
	@Column(name = "i_update_id")
	private Integer updateId;

	@JsonIgnore
	@Column(name = "b_deleted")
	private boolean deleted;

	public void prepareUpdate(Integer updateId) {
		this.setUpdateId(updateId);
		this.setUpdateDate(DateUtils.getCurrentDate());
		this.setDeleted(false);
	}

	public void prepareCreate(Integer createId) {
		this.setCreateId(createId);
		this.setCreateDate(DateUtils.getCurrentDate());
		this.setDeleted(false);
	}
}

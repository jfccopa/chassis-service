package com.threetrack.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class GenericEntity {

	@Column(name = "t_create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "i_create_id")
	private Integer createId;

	@Column(name = "t_update_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name = "i_update_id")
	private Integer updateId;

	@Column(name = "b_deleted")
	private boolean deleted;

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Integer getCreateId() {
		return this.createId;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public Integer getUpdateId() {
		return this.updateId;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public boolean getDeleted() {
		return this.deleted;
	}
	
}

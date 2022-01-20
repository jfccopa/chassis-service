package com.threetrack.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ad_login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "i_login_id")
	private Integer loginId;

	@Column(name = "i_user_id")
	private Integer userId;

	@Column(name = "t_login_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;

	@Column(name = "t_logout_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutDate;

	public Login() {}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public Integer getLoginId() {
		return this.loginId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public Date getLogoutDate() {
		return this.logoutDate;
	}
}

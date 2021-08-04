package com.threetrack.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

public class Account implements Serializable{

	private static final long serialVersionUID = 1L;

	protected String accountId;
	protected Integer credits;
	protected Integer maxCredits;

	public Account() {}

	public Account(String accountId, Integer credits, Integer maxCredits) {
		this.accountId = accountId;
		this.credits = credits;
		this.maxCredits = maxCredits;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Integer getCredits() {
		return this.credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public Integer getMaxCredits() {
		return this.maxCredits;
	}

	public void setMaxCredits(Integer maxCredits) {
		this.maxCredits = maxCredits;
	}
}

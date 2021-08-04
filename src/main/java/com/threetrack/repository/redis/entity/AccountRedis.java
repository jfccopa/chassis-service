package com.threetrack.repository.redis.entity;

import java.io.Serializable;

import com.threetrack.entity.Account;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Account")
public class AccountRedis extends Account{

	public AccountRedis() {}

	public AccountRedis(String accountId, Integer credits, Integer maxCredits) {
		super(accountId, credits,maxCredits);
	}

	@Id
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

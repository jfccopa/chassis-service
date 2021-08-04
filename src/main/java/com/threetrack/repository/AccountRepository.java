package com.threetrack.repository;

import com.threetrack.entity.Account;

/**
 * AccountRepository
 */
public interface AccountRepository {

	public Account getById(String accountId);
	public void add(Account account);
	public void update(Account account);

	public Long size();
}
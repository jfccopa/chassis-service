package com.threetrack.repository.redis;

import com.threetrack.entity.Account;
import com.threetrack.repository.AccountRepository;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

public class AccountRepositoryRedis implements AccountRepository {

	@Autowired private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, Object> hashOperations;

	private static final String KEY = "accounts";

	static FileWriter writerGET;
	static FileWriter writerUPDATE;

	public AccountRepositoryRedis() {
		if (this.redisTemplate != null)
			this.hashOperations = this.redisTemplate.opsForHash();
	}

	public Account getById(String accountId) {

		Date d1 = new Date();

		Account account = (Account) this.hashOperations.get(KEY, accountId);

		Date d2 = new Date();

		// log(writerGET, d2.getTime() - d1.getTime());

		return account;
	}
	public void add(Account account) {
		this.hashOperations.putIfAbsent(KEY, account.getAccountId(), account);
	}
	public void update(Account account) {

		Date d1 = new Date();

		this.hashOperations.put(KEY, account.getAccountId(), account);

		Date d2 = new Date();

		// log(writerUPDATE, d2.getTime() - d1.getTime());
	}
	public void remove(String accountId) {
		this.hashOperations.delete(KEY, accountId);
	}
	public Long size() {
		return this.hashOperations.size(KEY);
	}

	private void log(FileWriter fw, long time) {
		try {
			fw.write(time + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void shutdown() {
		try {
			if (writerGET != null)
				writerGET.close();
			if (writerUPDATE != null)
				writerUPDATE.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

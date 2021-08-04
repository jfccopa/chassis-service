package com.threetrack.service.impl1;

import com.threetrack.actuator.Actuator;
import com.threetrack.entity.Account;
import com.threetrack.repository.AccountRepository;
import com.threetrack.service.CreditService;

import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class CreditServiceImpl1 implements CreditService {

	@Autowired private AccountRepository accountRepository;

	public Account getCredits(String accountId) {
		return accountRepository.getById(accountId);
	}

	public Account debitCredits(String accountId) {
		synchronized (accountRepository) {
			Account account = accountRepository.getById(accountId);
			if (account != null) {
				account.setCredits(account.getCredits() - 1);
				accountRepository.update(account);
				Actuator.appendUpdate(account.getAccountId(), account.getCredits());
			}
			return account;
		}
	}

	public Account refundCredits(String accountId) {
		synchronized (accountRepository) {
			Account account = accountRepository.getById(accountId);
			if (account != null) {
				account.setCredits(account.getCredits() + 1);
				accountRepository.update(account);
				Actuator.appendUpdate(account.getAccountId(), account.getCredits());
			}
			return account;
		}
	}

	public Long accountNumber() {
		return accountRepository.size();
	}

	public Long loadCsv(String fileName) {

		try {

			BufferedReader in = new BufferedReader(new FileReader(fileName));

			String header = in.readLine();
			String line = in.readLine();
			Long i = 0L;
			while (line != null) {
				try {
					String[] fields = line.split(";");
					Account account = new Account(fields[0], Integer.valueOf(fields[1]), Integer.valueOf(fields[3]));
					accountRepository.add(account);
					i++;
				} catch (OutOfMemoryError e) {
					// e.getMessage();
					System.out.println(e);
				}
				line = in.readLine();
			}
			System.out.println("rows in file csv " + i);
			System.out.println("rows in data base " + accountRepository.size());
			in.close();
			return i;
		} catch (Exception e) {
			System.out.println(e);
			// e.printStackTrace();
		}
		return 0L;
	}
}

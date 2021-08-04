package com.threetrack.service.impl2;

import com.threetrack.actuator.Actuator;
import com.threetrack.entity.Account;
import com.threetrack.repository.AccountRepository;
import com.threetrack.service.CreditService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class CreditServiceImpl2 implements CreditService {

	List<Account> accounts = new ArrayList<Account>();

	public Account getCredits(String accountId) {

		return new Account();
	}

	public Account debitCredits(String accountId) {
		return new Account();
	}

	public Account refundCredits(String accountId) {
		return new Account();
	}

	public Long accountNumber() {
		return 20L;
	}

	public Long loadCsv(String fileName) {
		return 10L;
	}
}

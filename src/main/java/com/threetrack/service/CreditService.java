package com.threetrack.service;

import com.threetrack.actuator.Actuator;
import com.threetrack.entity.Account;
import com.threetrack.repository.AccountRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface CreditService {

	public Account getCredits(String accountId);

	public Account debitCredits(String accountId);	

	public Account refundCredits(String accountId);

	public Long accountNumber();

	public Long loadCsv(String fileName);
}

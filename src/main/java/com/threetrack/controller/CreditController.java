package com.threetrack.controller;

import com.threetrack.entity.Account;
import com.threetrack.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {

	@Autowired
	private CreditService creditService;

	@GetMapping("/{accountID}")
	public Account getCredits(@PathVariable("accountID") String accountID) {
		return creditService.getCredits(accountID);
	}

	@PutMapping("/debit/{accountID}")
	public Account debitCredits(@PathVariable("accountID") String accountID) {
		return creditService.debitCredits(accountID);
	}
	@PutMapping("/refund/{accountID}")
	public Account refundCredits(@PathVariable("accountID") String accountID) {
		return creditService.refundCredits(accountID);
	}
}

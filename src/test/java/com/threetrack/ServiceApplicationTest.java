package com.threetrack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ServiceApplicationTest {

	/*
	@Autowired private CreditService creditService;

	public String accountId = "1mundotrans.smscpaas";

	@Test
	@Order(1)
	void loadData() {
		
		String creditsCSV = "src/test/resources/creditos-contas-pos.csv";
		Long sizeLoad = creditService.loadCsv(creditsCSV);

		assertEquals(creditService.accountNumber(), sizeLoad);
	}

	@Test
	@Order(2)
	void getCredits() {

		Account account = creditService.getCredits(accountId);

		assertNotNull(account);
		assertEquals(accountId, account.getAccountId());
		assertNotEquals(0, account.getCredits());
	}

	@Test
	@Order(3)
	void debitCredits() {

		Account account = creditService.getCredits(accountId);
		Account accountPost = creditService.debitCredits(accountId);

		assertNotNull(account);
		assertNotNull(accountPost);
		assertEquals(account.getCredits() - 1, accountPost.getCredits());
	}

	@Test
	@Order(4)
	void refundCredits() {

		Account account = creditService.getCredits(accountId);
		Account accountPost = creditService.refundCredits(accountId);

		assertNotNull(account);
		assertNotNull(accountPost);
		assertEquals(account.getCredits() + 1, accountPost.getCredits());
	}

	 */
}

package com.threetrack.actuator;

import com.threetrack.entity.Product;
import com.threetrack.repository.ProductDao;
import com.threetrack.repository.postgress.ProductDaoJpa;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Actuator {

	private static Logger logger = LoggerFactory.getLogger(Actuator.class);

	private static Map<String, Integer> healthTable = new ConcurrentHashMap<>();

	@Autowired
	private ProductDao productDao;

	@Autowired
	public Actuator() {
		logger.info("init actuator");
	}

	@Scheduled(fixedDelayString = "${actuator.update_scheduled}")
	public void monitoring() {
		logger.info("monitoring >>>>");

		for (Product p : productDao.list()) {
			logger.info(p.getId() + " " + p.getName() + " " + p.getCreateDate());
		}

		Product p = new Product();
		p.setName("new Product");
		p.setDescription("");
		p.setOrganizationId(0);
		p.setCreateDate(new Date());
		p.setCreateId(0);

		productDao.add(p);

	}

	public static void appendUpdate(String account, Integer credits) {
		Actuator.healthTable.put(account, credits);
	}
}

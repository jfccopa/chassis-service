package com.threetrack.actuator;

import com.threetrack.entity.Product;
import com.threetrack.repository.ProductDao;
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
			String logProduct = String.format(
				"%s %s %s %s %d", p.getProductId(), p.getName(), p.getCreateDate(), p.getUpdateDate(), p.getQuantity());
			logger.info(logProduct);
		}
	}
}

package com.threetrack.actuator;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
	public Actuator() {
		logger.info("init actuator");
	}

	@Scheduled(fixedDelayString = "${actuator.update_scheduled}")
	public void monitoring() {
		logger.info("monitoring >>>>");

		if (!Actuator.healthTable.isEmpty()) {
			Set<Entry<String, Integer>> values = Actuator.healthTable.entrySet();

			StringBuilder stringBuilder = new StringBuilder();

			// send bash
			for (Entry<String, Integer> val : values) {
				stringBuilder.append("\n" + val.getKey() + " " + val.getValue());
			}
			Actuator.healthTable.clear();

			String s = stringBuilder.toString();

			logger.info(s);
		}
	}

	public static void appendUpdate(String account, Integer credits) {
		Actuator.healthTable.put(account, credits);
	}
}

package com.threetrack.repository;

import com.threetrack.repository.postgress.JPAConnection;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Autowired
	DataSourceConfig(@Value("${datasource.url}") String datasourceUrl,
	                 @Value("${datasource.user}") String datasourceUser,
	                 @Value("${datasource.password}") String datasourcePassword) {

		Properties properties = new Properties();
		properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
		properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
		properties.put("javax.persistence.jdbc.url", datasourceUrl + "?prepareThreshold=0");
		properties.put("javax.persistence.jdbc.user", datasourceUser);
		properties.put("javax.persistence.jdbc.password", datasourcePassword);
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.show_sql", "false");

		try {
			JPAConnection.buildEntityManagerFactory(properties);

		} catch (Exception e) {
			logger.error("No connection with database", e);
		}

		logger.info("connection with database done");
	}
}

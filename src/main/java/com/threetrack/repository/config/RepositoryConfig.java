package com.threetrack.repository.config;

import com.threetrack.repository.dao.ProductDao;
import com.threetrack.repository.dao.ProductDaoJpa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
/*
	@Bean
	public AccountRepository accountRepository(@Value("${repository.impl}") int repositoryImpl) {
		if (repositoryImpl == 0)
			return new AccountRepositoryRedis();
		return new AccountRepositoryRedis();
	}*/

	@Bean
	public ProductDao productDao(@Value("${repository.impl}") int repositoryImpl) {
		if (repositoryImpl == 0)
			return new ProductDaoJpa();
		return new ProductDaoJpa();
	}
}

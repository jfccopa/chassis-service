package com.threetrack.repository;

import com.threetrack.repository.redis.AccountRepositoryRedis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RepositoryConfig {

	@Bean
	public AccountRepository accountRepository(@Value("${repository.impl}") int repositoryImpl) {
		if (repositoryImpl == 0)
			return new AccountRepositoryRedis();
		return new AccountRepositoryRedis();
	}
}

package com.threetrack.service;

import com.threetrack.service.impl1.CreditServiceImpl1;
import com.threetrack.service.impl2.CreditServiceImpl2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class ServiceConfig {

	@Bean
	public CreditService creditService(@Value("${service.impl}") int serviceImpl) {
		if (serviceImpl == 0)
			return new CreditServiceImpl1();
		return new CreditServiceImpl2();
	}
}

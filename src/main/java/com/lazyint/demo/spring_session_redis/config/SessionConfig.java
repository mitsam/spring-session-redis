package com.lazyint.demo.spring_session_redis.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configurable
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*5)
public class SessionConfig extends AbstractHttpSessionApplicationInitializer{
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
	
	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		return HeaderHttpSessionIdResolver.xAuthToken();
	}
	
	@Bean
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6389)); 
	}
	
	
}

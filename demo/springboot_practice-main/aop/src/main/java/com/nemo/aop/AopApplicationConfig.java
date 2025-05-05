package com.nemo.aop;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nemo.aop.user.dao.UserDao;

@Configuration
public class AopApplicationConfig{

	@Bean
	public UserDao userDao(DataSource dataSource) {
		return new UserDao(dataSource);
	}

	@Bean
	public AopApplicationRunner aopApplicationRunner(DataSource dataSource, UserDao userDao) {
		return new AopApplicationRunner(dataSource, userDao);
	}
}

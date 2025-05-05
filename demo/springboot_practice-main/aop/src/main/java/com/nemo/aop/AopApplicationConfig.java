package com.nemo.aop;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.nemo.aop.user.dao.UserDao;
import com.nemo.aop.user.service.UserService;
import com.nemo.aop.user.service.UserServiceImpl;
import com.nemo.aop.user.service.UserServiceTx;

@Configuration
public class AopApplicationConfig{

	@Bean
	public UserDao userDao(DataSource dataSource) {
		return new UserDao(dataSource);
	}

	@Bean
	public UserServiceImpl userService(UserDao userDao) {
		return new UserServiceImpl(userDao);
	}

	@Bean
	@Primary
	public UserServiceTx userServiceTx(UserService userService, DataSource dataSource) {
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		return new UserServiceTx(userService, transactionManager);
	}

	@Bean
	public AopApplicationRunner aopApplicationRunner(UserService userService) {
		return new AopApplicationRunner(userService);
	}
}

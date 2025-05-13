package com.nemo.aop.v2;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
class UserConfig {
	private final DataSource datasource;

	public UserConfig(DataSource datasource) {
		this.datasource = datasource;
	}

	@Bean
	public UserDao userDao() {
		return new UserDaoImpl(datasource);
	}

	@Bean
	public UserService userServiceImpl(UserDao userDao) {
		return new UserServiceImpl(userDao);
	}

	@Bean
	@Primary
	public UserService userServiceTx(UserDao userDao) {
		UserService userServiceImpl = userServiceImpl(userDao);
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(datasource);
		return new UserServiceTx(transactionManager, userServiceImpl);
	}
}

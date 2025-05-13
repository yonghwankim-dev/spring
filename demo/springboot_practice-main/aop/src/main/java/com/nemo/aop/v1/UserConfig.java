package com.nemo.aop.v1;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public UserServiceImpl userServiceImpl(UserDao userDao) {
		return new UserServiceImpl(userDao, datasource);
	}
}

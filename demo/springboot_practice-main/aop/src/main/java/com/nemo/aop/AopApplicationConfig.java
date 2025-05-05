package com.nemo.aop;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopApplicationConfig{

	@Bean
	public DataSource dataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
		dataSource.setUser("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public AopApplicationRunner aopApplicationRunner(DataSource dataSource) {
		return new AopApplicationRunner(dataSource);
	}
}

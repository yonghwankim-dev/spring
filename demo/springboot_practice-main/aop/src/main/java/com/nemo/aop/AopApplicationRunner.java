package com.nemo.aop;

import javax.sql.DataSource;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class AopApplicationRunner implements ApplicationRunner {

	private final DataSource datasource;

	public AopApplicationRunner(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public void run(ApplicationArguments args) {
		upgradeLevels();
	}

	private void upgradeLevels(){
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(datasource);
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		System.out.println(status);
	}
}

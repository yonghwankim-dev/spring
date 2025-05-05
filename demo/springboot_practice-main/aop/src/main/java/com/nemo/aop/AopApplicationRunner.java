package com.nemo.aop;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.nemo.aop.user.dao.UserDao;
import com.nemo.aop.user.domain.User;

public class AopApplicationRunner implements ApplicationRunner {

	private final DataSource datasource;
	private final UserDao userDao;

	public AopApplicationRunner(DataSource datasource, UserDao userDao) {
		this.datasource = datasource;
		this.userDao = userDao;
	}

	@Override
	public void run(ApplicationArguments args) {
		upgradeLevels();
	}

	private void upgradeLevels(){
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(datasource);
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try{
			List<User> users = userDao.getAll();
			System.out.println("Before upgrade: " + users);
		}catch (Exception e){
			transactionManager.rollback(status);
			throw e;
		}

	}
}

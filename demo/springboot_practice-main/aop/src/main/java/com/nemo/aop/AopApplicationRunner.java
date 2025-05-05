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
import com.nemo.aop.user.domain.Level;
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
		printAllUsers();
	}

	private void upgradeLevels(){
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(datasource);
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try{
			upgradeLevelsInternal();
			transactionManager.commit(status);
		}catch (Exception e){
			transactionManager.rollback(status);
			throw e;
		}
	}

	private void upgradeLevelsInternal() {
		List<User> users = userDao.getAll();
		for (User user : users){
			if (canUpgradeLevel(user)){
				upgradeUser(user);
			}
		}
	}

	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		return currentLevel.nextLevel() != null;
	}

	private void upgradeUser(User user) {
		Level nextLevel = user.getLevel().nextLevel();
		userDao.update(user.getId(), nextLevel);
	}

	private void printAllUsers() {
		userDao.getAll().forEach(System.out::println);
	}
}

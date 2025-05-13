package com.nemo.aop.v1;

import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

class UserServiceImpl {

	private final UserDao userDao;
	private final DataSource datasource;
	private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

	UserServiceImpl(UserDao userDao, DataSource datasource) {
		this.userDao = userDao;
		this.datasource = datasource;
	}

	public void upgradeLevels() {
		long startTime = System.currentTimeMillis();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(datasource);
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			upgradeLevelsInternal();
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
		long endTime = System.currentTimeMillis();
		log.info("Upgrade levels took " + (endTime - startTime) + " ms");
	}

	private void upgradeLevelsInternal() {
		List<User> users = userDao.getAll();
		for (User user : users) {
			if (canUpgradeLevel(user)) {
				upgradeUser(user);
			}
		}
	}

	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		return currentLevel.nextLevel() != null;
	}

	private void upgradeUser(User user) {
		userDao.upgradeLevel(user);
	}

	public void add(User user) {
		userDao.add(user);
	}

	public void printAllUsers() {
		userDao.getAll().forEach(System.out::println);
	}
}

package com.nemo.aop.v2;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceTx implements UserService {

	private final PlatformTransactionManager transactionManager;
	private final UserService userService;

	public UserServiceTx(PlatformTransactionManager transactionManager, UserService userService) {
		this.transactionManager = transactionManager;
		this.userService = userService;
	}

	@Override
	public void upgradeLevels() {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			userService.upgradeLevels();
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		} finally {
			transactionManager.commit(status);
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@Override
	public void printAllUsers() {
		userService.printAllUsers();
	}

	@Override
	public void add(User user) {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			userService.add(user);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		} finally {
			transactionManager.commit(status);
		}
	}
}

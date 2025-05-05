package com.nemo.aop.user.service;

import java.util.List;

import com.nemo.aop.user.dao.UserDao;
import com.nemo.aop.user.domain.Level;
import com.nemo.aop.user.domain.User;

public class UserServiceImpl implements UserService {
	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void upgradeLevels() {
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

	@Override
	public void printAllUsers() {
		userDao.getAll().forEach(System.out::println);
	}

	@Override
	public void add(User user) {
		userDao.insert(user);
	}
}

package com.nemo.aop.v2;

import java.util.List;

class UserServiceImpl implements UserService {

	private final UserDao userDao;

	UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void upgradeLevels() {
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

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public void printAllUsers() {
		userDao.getAll().forEach(System.out::println);
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}
}

package com.nemo.aop.v2;

import java.util.List;

interface UserDao {
	List<User> getAll();

	void upgradeLevel(User user);

	void deleteAll();

	int getCount();

	void add(User user);

	User get(String id);
}

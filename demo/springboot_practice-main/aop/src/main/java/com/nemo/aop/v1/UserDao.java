package com.nemo.aop.v1;

import java.util.List;

interface UserDao {
	List<User> getAll();

	void upgradeLevel(User user);

	void deleteAll();

	int getCount();

	void add(User user);

	User get(String id);
}

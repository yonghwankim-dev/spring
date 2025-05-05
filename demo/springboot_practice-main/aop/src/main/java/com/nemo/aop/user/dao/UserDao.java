package com.nemo.aop.user.dao;

import java.util.List;

import com.nemo.aop.user.domain.User;

public interface UserDao {
	List<User> getAll();

	void update(User user);

	void deleteAll();

	int getCount();

	void add(User user);
	
	User get(String id);
}

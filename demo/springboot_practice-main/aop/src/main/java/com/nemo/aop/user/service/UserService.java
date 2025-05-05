package com.nemo.aop.user.service;

import java.util.List;

import com.nemo.aop.user.domain.User;

public interface UserService {
	void upgradeLevels();

	List<User> getAllUsers();

	void printAllUsers();

	void add(User user);
}

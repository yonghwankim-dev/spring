package com.nemo.aop.user.service;

import com.nemo.aop.user.domain.User;

public interface UserService {
	void upgradeLevels();

	void printAllUsers();

	void add(User user);
}

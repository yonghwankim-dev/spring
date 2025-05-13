package com.nemo.aop.v2;

import java.util.List;

public interface UserService {
	void upgradeLevels();

	List<User> getAllUsers();

	void printAllUsers();

	void add(User user);
}

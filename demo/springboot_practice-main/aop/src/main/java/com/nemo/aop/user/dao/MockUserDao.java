package com.nemo.aop.user.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nemo.aop.user.domain.User;

public class MockUserDao implements UserDao {

	private final List<User> users;
	private final List<User> updated;

	public MockUserDao(List<User> users) {
		this.users = users;
		this.updated = new ArrayList<>();
	}

	@Override
	public List<User> getAll() {
		return Collections.unmodifiableList(users);
	}

	@Override
	public void update(User user) {
		this.updated.add(user);
	}

	public List<User> getUpdated() {
		return Collections.unmodifiableList(updated);
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getCount() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public User get(String id) {
		throw new UnsupportedOperationException();
	}
}

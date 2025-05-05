package com.nemo.aop.user.domain;

public class User {
	private long id;
	private String name;
	private String level;

	public User(long id, String name, String level) {
		this.id = id;
		this.name = name;
		this.level = level;
	}

	@Override
	public String toString() {
		return String.format("User{id=%d, name='%s', level='%s'}", id, name, level);
	}
}

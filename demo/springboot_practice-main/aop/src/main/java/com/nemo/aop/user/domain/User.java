package com.nemo.aop.user.domain;

public class User {
	private final long id;
	private final String name;
	private final Level level;

	public User(long id, String name, Level level) {
		this.id = id;
		this.name = name;
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Level getLevel() {
		return level;
	}

	@Override
	public String toString() {
		return String.format("User{id=%d, name='%s', level='%s'}", id, name, level);
	}
}

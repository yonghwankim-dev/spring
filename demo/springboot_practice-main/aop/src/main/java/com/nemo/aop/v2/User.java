package com.nemo.aop.v2;

class User {
	private final Long id;
	private final String name;
	private final Level level;

	public User(String name, Level level) {
		this(null, name, level);
	}

	public User(Long id, String name, Level level) {
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

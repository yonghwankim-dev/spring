package com.nemo.aop.v1;

enum Level {
	BASIC, SILVER, GOLD;

	public Level nextLevel() {
		return switch (this) {
			case BASIC -> SILVER;
			case SILVER -> GOLD;
			case GOLD -> null;
			default -> throw new IllegalArgumentException("Unknown level: " + this);
		};
	}
}

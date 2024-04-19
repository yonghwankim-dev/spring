package com.oauth;

import java.util.Arrays;

public enum Role {
	GUEST("ROLE_GUEST"),
	USER("ROLE_USER");

	private final String key;

	Role(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static Role from(String key) {
		return Arrays.stream(values())
			.filter(role -> role.key.equals(key))
			.findAny()
			.orElseThrow();
	}
}

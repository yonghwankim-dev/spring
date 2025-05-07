package com.nemo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Member implements java.io.Serializable {
	private Long id;
	private String name;
	private int age;

	public Member() {
	}

	public Member(Long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
}

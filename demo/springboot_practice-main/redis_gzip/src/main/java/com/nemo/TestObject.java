package com.nemo;

import java.io.Serializable;

import lombok.Getter;

@Getter
class TestObject implements Serializable {
	private String name;
	private int age;

	public TestObject() {
	}

	public TestObject(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

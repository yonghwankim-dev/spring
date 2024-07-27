package com.ch11.class03.step01;

import java.util.Optional;

public class Person {

	public static void main(String[] args) {
		Insurance insurance = new Insurance("name");
		Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
		Optional<String> name = optionalInsurance.map(Insurance::getName);
		name.ifPresent(System.out::println);
	}
}

class Insurance {
	private String name;

	public Insurance(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

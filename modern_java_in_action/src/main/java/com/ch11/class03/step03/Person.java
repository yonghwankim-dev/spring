package com.ch11.class03.step03;

import java.util.Optional;

public class Person {
	private Car car;

	public Optional<Car> getCarAsOptional() {
		return Optional.ofNullable(car);
	}

	public static void main(String[] args) {
		Car car = new Person().getCarAsOptional()
			.orElse(null);
		System.out.println(car); // null
	}
}

class Car{
	private Insurance insurance;

	public Optional<Insurance> getInsuranceAsOptional() {
		return Optional.ofNullable(insurance);
	}
}

class Insurance{
	private String name;

	public String getName() {
		return name;
	}
}

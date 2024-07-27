package com.ch11.class03.step02;

import java.util.Optional;

public class Person {
	private Optional<Car> car;

	public Person(Car car) {
		this.car = Optional.ofNullable(car);
	}

	public Optional<Car> getCar() {
		return car;
	}

	public static void main(String[] args) {
		String result = getCarInsuranceName(Optional.of(new Person(new Car(new Insurance(null)))));
		System.out.println(result); // Unknown
	}

	private static String getCarInsuranceName(Optional<Person> person) {
		return person.flatMap(Person::getCar)
			.flatMap(Car::getInsurance)
			.map(Insurance::getName)
			.orElse("Unknown");
	}
}

class Car {
	private Optional<Insurance> insurance;

	public Car(Insurance insurance) {
		this.insurance = Optional.ofNullable(insurance);
	}

	public Optional<Insurance> getInsurance() {
		return insurance;
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

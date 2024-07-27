package com.ch11.class03.step07;

import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		Person p1 = new Person(Optional.of(new Car(Optional.of(new Insurance("Company A")))), 30);
		String result = getInsuranceName(Optional.of(p1), 20);
		System.out.println(result);
	}

	public static String getInsuranceName(Optional<Person> person, int minAge) {
		return person.filter(p->p.getAge() >= minAge)
			.flatMap(Person::getCar)
			.flatMap(Car::getInsurance)
			.map(Insurance::getName)
			.orElse("Unknown");
	}
}

class Person{
	private Optional<Car> car;
	private int age;

	public Person(Optional<Car> car, int age) {
		this.car = car;
		this.age = age;
	}

	public Optional<Car> getCar() {
		return car;
	}

	public int getAge() {
		return age;
	}
}

class Car{
	private Optional<Insurance> insurance;

	public Car(Optional<Insurance> insurance) {
		this.insurance = insurance;
	}

	public Optional<Insurance> getInsurance() {
		return insurance;
	}
}

class Insurance{
	private String name;

	public Insurance(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

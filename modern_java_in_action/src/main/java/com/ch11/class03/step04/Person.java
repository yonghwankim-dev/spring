package com.ch11.class03.step04;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Person {
	private Optional<Car> car;

	public Person(Car car) {
		this.car = Optional.ofNullable(car);
	}

	public Optional<Car> getCar() {
		return car;
	}

	public static void main(String[] args) {
		Person p1 = new Person(new Car(new Insurance("a")));
		Person p2 = new Person(new Car(new Insurance("b")));
		System.out.println(getCarInsuranceNames(List.of(p1, p2))); // (a,b)
	}

	private static Set<String> getCarInsuranceNames(List<Person> persons) {
		return persons.stream()
			.map(Person::getCar)
			.map(optCar -> optCar.flatMap(Car::getInsurance))
			.map(optIns -> optIns.map(Insurance::getName))
			.flatMap(Optional::stream)
			.collect(Collectors.toSet());

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

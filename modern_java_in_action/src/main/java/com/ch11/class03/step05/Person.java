package com.ch11.class03.step05;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Person {
	private Car car;

	public Person(Car car) {
		this.car = car;
	}

	public static void main(String[] args) {
		Car car = new Car(null);
		Person p1 = new Person(car);
		Optional<Insurance> insurance = nullSafeFindCheapestInsurance(Optional.of(p1), Optional.of(car));
		insurance.map(Insurance::getName)
			.ifPresent(System.out::println);
	}

	// Person과 Car 정보를 이용해서 가장 저렴한 보험료를 제공하는 보험회사를 찾기
	private static Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car){
		if (person.isPresent() && car.isPresent()){
			return Optional.of(findCheapestInsurance(person.get(), car.get()));
		}
		return Optional.empty();
	}

	private static Optional<Insurance> nullSafeFindCheapestInsuranceV2(Optional<Person> person, Optional<Car> car){
		return person.flatMap(p->car.map(c->findCheapestInsurance(p, c)));
	}

	private static Insurance findCheapestInsurance(Person person, Car car) {
		return new Insurance("company A", 1000);
	}
}

class Car{
	private Insurance insurance;

	public Car(Insurance insurance) {
		this.insurance = insurance;
	}
}

class Insurance{
	private String name;
	private int price;

	public Insurance(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}

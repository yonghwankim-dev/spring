package com.ch11.class02.step01;

import java.util.Optional;

import javax.swing.text.html.Option;

public class Person {
	private Optional<Car> car;

	public Person(Optional<Car> car) {
		this.car = car;
	}

	public Optional<Car> getCar() {
		return car;
	}

	public static void main(String[] args) {
		Person person = new Person(Optional.of(new Car(Optional.of(new Insurance(null)))));
		String carInsuranceName = getCarInsuranceName(person);
		System.out.println(carInsuranceName); // null
	}

	private static String getCarInsuranceName(Person person){
		return person.getCar()
			.orElse(null)
			.getInsurance()
			.orElse(null)
			.getName();
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

class Insurance {
	private String name;

	public Insurance(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

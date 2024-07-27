package com.ch11.class01.step01;

public class Person {
	private Car car;

	public Person(Car car) {
		this.car = car;
	}

	public Car getCar() {
		return car;
	}

	public static void main(String[] args) {
		Person person = new Person(new Car(null));
		String carInsuranceName = getCarInsuranceName(person); // NullPointerException
		System.out.println(carInsuranceName);
	}

	private static String getCarInsuranceName(Person person){
		return person.getCar().getInsurance().getName();
	}
}

class Car {
	private Insurance insurance;

	public Car(Insurance insurance) {
		this.insurance = insurance;
	}

	public Insurance getInsurance() {
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

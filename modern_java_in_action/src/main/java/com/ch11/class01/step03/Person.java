package com.ch11.class01.step03;

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
		String carInsuranceName = getCarInsuranceName(person); // Unknown
		System.out.println(carInsuranceName);
	}

	private static String getCarInsuranceName(Person person){
		if (person == null){
			return "Unknown";
		}
		Car car = person.getCar();
		if (car == null) {
			return "Unknown";
		}
		Insurance insurance = car.getInsurance();
		if (insurance == null) {
			return "Unknown";
		}
		return insurance.getName();
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

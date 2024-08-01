package com.ch09.class02.step02;

public class Validator {
	interface ValidationStrategy{
		boolean execute(String s);
	}
	private final ValidationStrategy strategy;

	public Validator(ValidationStrategy strategy) {
		this.strategy = strategy;
	}

	public boolean validate(String s){
		return strategy.execute(s);
	}

	public static void main(String[] args) {
		Validator numericValidator = new Validator(s->s.matches("\\d"));
		System.out.println(numericValidator.validate("aaaa")); // false

		Validator lowerCaseValidator = new Validator(s->s.matches("[a-z]+"));
		System.out.println(lowerCaseValidator.validate("aaaa")); // true
	}
}

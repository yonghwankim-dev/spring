package com.ch09.class02.step01;

public class Validator {
	interface ValidationStrategy{
		boolean execute(String s);
	}

	static class IsAllLowerCase implements ValidationStrategy{
		@Override
		public boolean execute(String s) {
			return s.matches("[a-z]+");
		}
	}

	static class IsNumeric implements ValidationStrategy{
		@Override
		public boolean execute(String s) {
			return s.matches("\\d+");
		}
	}

	private final ValidationStrategy strategy;

	public Validator(ValidationStrategy strategy) {
		this.strategy = strategy;
	}

	public boolean validate(String s){
		return strategy.execute(s);
	}

	public static void main(String[] args) {
		Validator numericValidator = new Validator(new IsNumeric());
		System.out.println(numericValidator.validate("aaaa")); // false

		Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
		System.out.println(lowerCaseValidator.validate("aaaa")); // true
	}
}

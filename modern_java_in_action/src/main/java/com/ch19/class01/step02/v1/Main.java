package com.ch19.class01.step02.v1;

public class Main {
	public static void main(String[] args) {
		double celsius = 40;
		double fahrenheit = converter(celsius, 9.0/5, 32);
		System.out.println(fahrenheit); // output: 104.0
	}
	
	private static double converter(double x, double f, double b){
		return x * f + b;
	}
}

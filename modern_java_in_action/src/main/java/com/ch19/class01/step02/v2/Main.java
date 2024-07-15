package com.ch19.class01.step02.v2;

import java.util.function.DoubleUnaryOperator;

public class Main {
	public static void main(String[] args) {
		double celsius = 40;
		DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
		DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
		DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

		double fahrenheit = convertCtoF.applyAsDouble(celsius);
		double gbp = convertUSDtoGBP.applyAsDouble(1000);
		double mi = convertKmtoMi.applyAsDouble(1000);

		System.out.println(fahrenheit); // output: 104.0
		System.out.println(gbp); // output: 600
		System.out.println(mi); // output: 	621.4
	}

	private static DoubleUnaryOperator curriedConverter(double f, double b){
		return (double x) -> x * f + b;
	}
}

package com.ch10.class03.step06;

public class Tax {
	public static double regional(double value) {
		return value * 1.1;
	}

	public static double general(double value) {
		return value * 1.3;
	}

	public static double surcharge(double value) {
		return value * 1.05;
	}

	public static double calculateTax(Order order, boolean useRegional, boolean useGeneral, boolean useSurcharge) {
		double value = order.getValue();
		if (useRegional){
			value = Tax.regional(value);
		}
		if (useGeneral){
			value = Tax.general(value);
		}
		if (useSurcharge){
			value = Tax.surcharge(value);
		}
		return value;
	}
}

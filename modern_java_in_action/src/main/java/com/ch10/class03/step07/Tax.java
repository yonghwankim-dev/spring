package com.ch10.class03.step07;

import com.ch10.class03.step06.Order;

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
}

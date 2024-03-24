package com.spring.temperature.step1;

import lombok.Getter;
@Getter
public class Temperature {
	private final double value;

	public Temperature(double value) {
		this.value = value;
	}
}

package com.spring.temperature.step2;

import lombok.Getter;

@Getter
public class Temperature {
	private final double value;

	public Temperature(double value) {
		this.value = value;
	}
}

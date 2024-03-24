package com.ch2.class03.step03;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Apple {
	private Color color;
	private int weight;

	public Apple(Color color, int weight) {
		this.color = color;
		this.weight = weight;
	}
}

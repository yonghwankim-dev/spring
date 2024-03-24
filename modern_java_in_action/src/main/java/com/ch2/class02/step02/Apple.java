package com.ch2.class02.step02;

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

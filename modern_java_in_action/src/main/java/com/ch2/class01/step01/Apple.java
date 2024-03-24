package com.ch2.class01.step01;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Apple {
	private Color color;

	public Apple(Color color) {
		this.color = color;
	}
}

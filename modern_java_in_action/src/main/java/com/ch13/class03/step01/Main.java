package com.ch13.class03.step01;

public class Main {
	public static void main(String[] args) {
		Monster m = new Monster();
		m.rotateBy(180);
		m.moveVertically(10);
		System.out.println(m);

		Sun sun = new Sun();
		sun.rotateBy(100);
		sun.moveHorizontally(10);
		System.out.println(sun);
	}
}

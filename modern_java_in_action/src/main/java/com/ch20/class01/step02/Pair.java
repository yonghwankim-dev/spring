package com.ch20.class01.step02;

public class Pair<X, Y> {
	public final X x;
	public final Y y;

	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public static void main(String[] args) {
		Pair<String, String> raoul = new Pair<>("Raoul", "+ 44 007007007");
		Pair<String, String> alan = new Pair<>("Alan", "+44 0031133700");
	}
}

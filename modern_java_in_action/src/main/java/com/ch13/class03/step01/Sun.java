package com.ch13.class03.step01;

public class Sun implements Movable, Rotatable{

	private int x;
	private int y;
	private int degree;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void setRotationAngle(int angleInDegrees) {
		this.degree = angleInDegrees;
	}

	@Override
	public int getRotationAngle() {
		return degree;
	}

	@Override
	public String toString() {
		return "Sun{" +
			"x=" + x +
			", y=" + y +
			", degree=" + degree +
			'}';
	}
}

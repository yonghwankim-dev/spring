package com.ch13.class03.step01;

public class Monster implements Rotatable, Movable, Resizable {

	private int width;
	private int height;
	private int x;
	private int y;
	private int degree;

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void setAbsoluteSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

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
		return "Monster{" +
			"width=" + width +
			", height=" + height +
			", x=" + x +
			", y=" + y +
			", degree=" + degree +
			'}';
	}
}

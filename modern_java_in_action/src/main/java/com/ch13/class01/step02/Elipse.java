package com.ch13.class01.step02;

public class Elipse implements Resizable {

	private int width;
	private int height;

	@Override
	public void draw() {
		System.out.printf("draw the Elipse width=%d, height=%d\n", width, height);
	}

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
	public void setRelativeSize(int wFactor, int hFactor) {
		// must to implement the interface's method
	}
}

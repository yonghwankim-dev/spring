package com.ch13.class01.step02;

public interface Resizable extends Drawable {
	int getWidth();
	int getHeight();
	void setWidth(int width);
	void setHeight(int height);
	void setAbsoluteSize(int width, int height);
	void setRelativeSize(int wFactor, int hFactor); // api version 2 method
}

package com.ch13.class02.step01;

public interface Resizable extends Drawable, Sized {
	int getWidth();
	int getHeight();
	void setWidth(int width);
	void setHeight(int height);
	void setAbsoluteSize(int width, int height);
	default void setRelativeSize(int widthFactor, int heightFactor){
		setAbsoluteSize(getWidth() / widthFactor, getHeight() / heightFactor);
	}
}

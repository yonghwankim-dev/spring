package com.ch13.class03.step01;

import com.ch13.class02.step01.Drawable;
import com.ch13.class02.step01.Sized;

public interface Resizable {
	int getWidth();
	int getHeight();
	void setWidth(int width);
	void setHeight(int height);
	void setAbsoluteSize(int width, int height);
	default void setRelativeSize(int widthFactor, int heightFactor){
		setAbsoluteSize(getWidth() / widthFactor, getHeight() / heightFactor);
	}
}

package com.ch13.class03.step01;

public interface Movable {
	int getX();
	int getY();
	void setX(int x);
	void setY(int y);

	default void moveHorizontally(int distance){
		setX(getX() + distance);
	}

	default void moveVertically(int distance){
		setY(getY() + distance);
	}
}

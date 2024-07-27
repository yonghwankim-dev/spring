package com.ch13.class01.step02;

import java.util.Arrays;
import java.util.List;

public class Game {
	public static void main(String[] args) {
		List<Resizable> resizableShapes = Arrays.asList(new Elipse());
		Utils.paint(resizableShapes);
	}
}

class Utils{
	public static void paint(List<Resizable> resizeableShapes){
		resizeableShapes.forEach(r->{
			r.setAbsoluteSize(42, 42);
			r.draw();
		});
	}
}

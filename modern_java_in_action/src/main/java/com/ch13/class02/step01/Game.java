package com.ch13.class02.step01;

import java.util.Arrays;
import java.util.List;

public class Game {
	public static void main(String[] args) {
		List<Resizable> resizableShapes = Arrays.asList(new Elipse());
		Utils.paint(resizableShapes);

		resizableShapes.forEach(r->{
			r.setAbsoluteSize(42, 42);
			r.setRelativeSize(2, 2);
			r.draw();
		});
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

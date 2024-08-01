package com.ch09.class02.step06;

public class HeaderTextProcessing extends ProcessingObject<String> {
	@Override
	protected String handleWork(String input) {
		return "From Raoul, Mario and Alan: " + input;
	}
}

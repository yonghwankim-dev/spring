package com.ch09.class02.step06;

public class SpellCheckerProcessing extends ProcessingObject<String> {
	@Override
	protected String handleWork(String text) {
		return text.replaceAll("labda", "lambda");
	}
}

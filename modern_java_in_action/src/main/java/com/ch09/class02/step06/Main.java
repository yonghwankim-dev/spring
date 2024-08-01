package com.ch09.class02.step06;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Main {
	public static void main(String[] args) {
		ProcessingObject<String> p1 = new HeaderTextProcessing();
		ProcessingObject<String> p2 = new SpellCheckerProcessing();
		p1.setSuccessor(p2);
		String result = p1.handle("Aren't labdas really sexy?!!");
		System.out.println(result);

		UnaryOperator<String> headerProcessing = (String text)->"From Raoul, Mario and Alan: " + text;
		UnaryOperator<String> spellCheckerProcessing = (String text)->text.replaceAll("labda", "lambda");
		Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
		result = pipeline.apply("Aren't labdas really sexy?!!");
		System.out.println(result);
	}
}

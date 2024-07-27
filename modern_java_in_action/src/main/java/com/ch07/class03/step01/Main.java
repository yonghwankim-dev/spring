package com.ch07.class03.step01;

public class Main {
	public int countWordsIteratively(String s){
		int counter = 0;
		boolean lastSpace = true;
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace) {
					counter++;
				}
				lastSpace = false;
			}
		}
		return counter;
	}

	public static void main(String[] args) {
		Main main = new Main();
		final String SENTENCE = "Nel   mezzo del cammin di nostra  vita " +
				"mi ritrovai in una  selva oscura " +
				"ch la dritta via era smarrita ";
		System.out.println("Found " + main.countWordsIteratively(SENTENCE) + " words");
	}
}

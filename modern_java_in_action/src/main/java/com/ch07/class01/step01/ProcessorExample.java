package com.ch07.class01.step01;

public class ProcessorExample {
	public static void main(String[] args) {
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println(processors);
	}
}

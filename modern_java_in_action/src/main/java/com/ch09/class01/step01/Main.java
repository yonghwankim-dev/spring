package com.ch09.class01.step01;

public class Main {
	interface Task{
		void execute();
	}

	public static void doSomething(Runnable r) {
		r.run();
	}

	public static void doSomething(Task a) {
		a.execute();
	}

	public static void main(String[] args) {
		doSomething(new Task() {
			@Override
			public void execute() {
				System.out.println("Danger, Danger!!");
			}
		});

		doSomething((Task)()->System.out.println("Danger, Danger!!"));
	}
}

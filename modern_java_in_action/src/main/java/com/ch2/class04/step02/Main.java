package com.ch2.class04.step02;

/**
 * Runnable로 코드 블록 실행하기
 * Thread 생성자에 Runnable 타입 인스턴스 전달
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		// 익명 클래스
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world");
			}
		});
		t.start();
		t.join();

		// 람다 표현식
		Thread t2 = new Thread(() -> System.out.println("hello world"));
		t2.start();
		t2.join();
	}
}

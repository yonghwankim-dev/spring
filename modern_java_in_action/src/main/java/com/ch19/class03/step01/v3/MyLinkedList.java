package com.ch19.class03.step01.v3;

public class MyLinkedList<T> implements MyList<T>{
	private final T head;
	private final MyList<T> tail;

	public MyLinkedList(T head, MyList<T> tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public T head() {
		return head;
	}

	@Override
	public MyList<T> tail() {
		return tail;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String toString() {
		return "MyLinkedList{" +
			"head=" + head +
			", tail=" + tail +
			'}';
	}

	public static void main(String[] args) {
		MyList<Integer> myList = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
		System.out.println(myList);
	}
}

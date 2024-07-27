package com.ch19.class05.step01;

public class Tree {
	private final String key;
	private final int value;
	private final Tree left, right;
	public Tree(String key, int value, Tree left, Tree right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public boolean isInRange(int start, int end) {
		return value >= start && value <= end;
	}

	public Tree getLeft() {
		return left;
	}

	public Tree getRight() {
		return right;
	}
}

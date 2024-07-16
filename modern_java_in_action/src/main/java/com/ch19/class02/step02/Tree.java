package com.ch19.class02.step02;

public class Tree {
	private String key;
	private int value;
	private Tree left, right;

	public Tree(String key, int value, Tree left, Tree right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "Tree{" +
			"key='" + key + '\'' +
			", value=" + value +
			", left=" + left +
			", right=" + right +
			'}';
	}

	static class TreeProcessor{
		public static Tree update(String key, int newValue, Tree tree){
			if (tree == null){
				tree = new Tree(key, newValue, null, null);
			}else if (key.equals(tree.key)){
				tree.value = newValue;
			}else if (key.compareTo(tree.key) < 0){
				tree.left = update(key, newValue, tree.left);
			}else{
				tree.right = update(key, newValue, tree.right);
			}
			return tree;
		}

		public static Tree functionalUpdate(String key, int newValue, Tree tree){
			return (tree == null) ?
				new Tree(key, newValue, null, null) :
					key.equals(tree.key) ?
						new Tree(key, newValue, tree.left, tree.right) :
					key.compareTo(tree.key) < 0 ?
						new Tree(tree.key, tree.value, functionalUpdate(key, newValue, tree.left), tree.right) :
						new Tree(tree.key, tree.value, tree.left, functionalUpdate(key, newValue, tree.right));
		}
	}

	public static void main(String[] args) {
		Tree a = new Tree("a", 150, null, null);
		Tree b = new Tree("b", 160, null, null);
		Tree c = new Tree("c", 170, null, null);
		b.left = a;
		b.right = c;

		Tree result = TreeProcessor.update("b", 155, b);
		System.out.println(result); // Tree{key='b', value=155, left=Tree{key='a', value=150, left=null, right=null}, right=Tree{key='c', value=170, left=null, right=null}}
		System.out.println(b); // Tree{key='b', value=155, left=Tree{key='a', value=150, left=null, right=null}, right=Tree{key='c', value=170, left=null, right=null}}

		a = new Tree("a", 150, null, null);
		b = new Tree("b", 160, null, null);
		c = new Tree("c", 170, null, null);
		b.left = a;
		b.right = c;
		result = TreeProcessor.functionalUpdate("b", 155, b);
		System.out.println(result); // Tree{key='b', value=155, left=Tree{key='a', value=150, left=null, right=null}, right=Tree{key='c', value=170, left=null, right=null}}
		System.out.println(b); // Tree{key='b', value=160, left=Tree{key='a', value=150, left=null, right=null}, right=Tree{key='c', value=170, left=null, right=null}}
	}
}


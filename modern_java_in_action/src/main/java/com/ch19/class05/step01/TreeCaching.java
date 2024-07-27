package com.ch19.class05.step01;

import java.util.HashMap;
import java.util.Map;

public class TreeCaching {
	private final Map<Range, Integer> numberOfNodes = new HashMap<>();

	public Integer computeNumberOfNodesUsingCache(Tree tree, Range range){
		Integer result = numberOfNodes.get(range);
		if (result != null) {
			System.out.println("Using cache range" + range);
			return result;
		}
		result = computeNumberOfNodes(tree, range);
		numberOfNodes.put(range, result);
		return result;
	}


	private Integer computeNumberOfNodes(Tree tree, Range range) {
		if (tree == null){
			return 0;
		}
		int count = 0;
		if (range.isInRange(tree)){
			count = 1;
		}
		count += computeNumberOfNodes(tree.getLeft(), range);
		count += computeNumberOfNodes(tree.getRight(), range);
		return count;
	}

	public static void main(String[] args) {
		Tree lee = new Tree("lee", 40, null, null);
		Tree park = new Tree("park", 60, null, null);
		Tree kim = new Tree("kim", 50, lee, park);

		TreeCaching treeCaching = new TreeCaching();
		Integer result = treeCaching.computeNumberOfNodesUsingCache(kim, new Range(50, 60));
		System.out.println(result); // 2

		result = treeCaching.computeNumberOfNodesUsingCache(kim, new Range(50, 60));
		System.out.println(result); // 2

		Range range1 = new Range(50, 60);
		Range range2 = new Range(50, 60);
		System.out.println(range1 == range2);
		System.out.println(range1.equals(range2));
	}
}

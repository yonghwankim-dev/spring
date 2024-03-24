package com.ch18.class01.step01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {1, 4, 9} 집합이 주어질때 해당 집합의 부분집합을 구하는 예제
 * - 함수형 프로그래밍 방식으로 구현
 * - 공집합(빈 리스트)도 부분집합으로 취급한다
 */
public class Main {
	public static void main(String[] args) {
		List<List<Integer>> result = subsets(List.of(1, 4, 9));
		System.out.println(result);
	}

	public static List<List<Integer>> subsets(List<Integer> list){
		if (list.isEmpty()){
			List<List<Integer>> ans = new ArrayList<>();
			ans.add(Collections.emptyList());
			return ans;
		}
		Integer first = list.get(0);
		List<Integer> rest = list.subList(1, list.size());

		List<List<Integer>> subans = subsets(rest);
		List<List<Integer>> subans2 = insertAll(first, subans);
		return concat(subans, subans2);
	}

	private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
		List<List<Integer>> result = new ArrayList<>();

		for (List<Integer> list : lists){
			List<Integer> copyList = new ArrayList<>();
			copyList.add(first);
			copyList.addAll(list);
			result.add(copyList);
		}
		return result;
	}

	private static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
		List<List<Integer>> r = new ArrayList<>(a);
		r.addAll(b);
		return r;
	}
}

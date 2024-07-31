package com.ch08.class02.step02;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		List<String> referenceCodes = new ArrayList<>();
		referenceCodes.add("a12");
		referenceCodes.add("C14");
		referenceCodes.add("b13");

		// 새로운 문자열 리스트를 생성함
		referenceCodes.stream()
			.map(code->Character.toUpperCase(code.charAt(0)) + code.substring(1))
			.collect(Collectors.toList())
			.forEach(System.out::println);

		// 기존 컬렉션을 수정함
		for (ListIterator<String> iterator = referenceCodes.listIterator(); iterator.hasNext(); ) {
			String code = iterator.next();
			iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
		}
		System.out.println(referenceCodes);

		referenceCodes = new ArrayList<>();
		referenceCodes.add("a12");
		referenceCodes.add("C14");
		referenceCodes.add("b13");
		referenceCodes.replaceAll(code->Character.toUpperCase(code.charAt(0)) + code.substring(1));
		System.out.println(referenceCodes);
	}
}

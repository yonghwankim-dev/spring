package com.example.debug.class01;

import java.util.HashMap;
import java.util.Map;

import com.example.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

/**
 * Non-debug mode
 */
public class DebugMonoExample03 {
	public static Map<String, String> fruits = new HashMap<>();

	static{
		fruits.put("banana", "바나나");
		fruits.put("apple", "사과");
		fruits.put("pear", "배");
		fruits.put("grape", "포도");
	}
	public static void main(String[] args) {
		Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
			.map(String::toLowerCase)
			.map(fruit -> fruit.substring(0, fruit.length() - 1))
			.map(fruits::get)
			.subscribe(Logger::onNext, Logger::onError);
	}
}

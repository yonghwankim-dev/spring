package com.ch05.class07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Dish {

	enum Type {
		MEAT, FISH, OTHER
	}

	private String name;
	private int calories;
	private Type type;
	private boolean isVegetarian;

	public Dish(String name, int calories, Type type, boolean isVegetarian) {
		this.name = name;
		this.calories = calories;
		this.type = type;
		this.isVegetarian = isVegetarian;
	}

	public String getName() {
		return name;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	@Override
	public String toString() {
		return name;
	}

	public static void main(String[] args) {
		Map<String, List<String>> dishTags = new HashMap<>();
		dishTags.put("pork", Arrays.asList("greasy", "salty"));
		dishTags.put("beef", Arrays.asList("salty", "roasted"));
		dishTags.put("chicken", Arrays.asList("fried", "crisp"));
		dishTags.put("french fries", Arrays.asList("greasy", "fried"));
		dishTags.put("rice", Arrays.asList("light", "natural"));
		dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
		dishTags.put("pizza", Arrays.asList("tasty", "salty"));
		dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
		dishTags.put("salmon", Arrays.asList("delicious", "fresh"));

		List<Dish> menu = Arrays.asList(
			new Dish("pork", 800, Type.MEAT, false),
			new Dish("beef", 700, Type.MEAT, false),
			new Dish("chicken", 400, Type.MEAT, false),
			new Dish("french fries", 530, Type.OTHER, false),
			new Dish("rice", 350, Type.OTHER, true),
			new Dish("season fruit", 120, Type.OTHER, true),
			new Dish("pizza", 550, Type.OTHER, false),
			new Dish("prawns", 400, Type.FISH, false),
			new Dish("salmon", 450, Type.FISH, false));

		testMapToInt(menu);
		testBoxed(menu);
		testRangeClosed();
		testPythagoras();
		testStreamOf();
		testStreamOfNullable();
		testIterate();
		testFibonacci();
		testIteratePredicate();
		testGenerate();
		testGenerateForFibonacci();
	}


	// 기본형 특화 스트림(IntStream, LongStream, DoubleStream)을 사용하여 언박싱 비용을 줄인다
	// 모든 메뉴의 칼로리 합을 계산
	private static void testMapToInt(List<Dish> menu) {
		int totalCalories = menu.stream()
			.mapToInt(Dish::getCalories)
			.sum();
		System.out.println("모든 메뉴 총 칼로리 : " + totalCalories);
	}

	// 기본형 특화 스트림(IntStream)을 Stream<Integer>로 변환

	private static void testBoxed(List<Dish> menu) {
		IntStream intStream = menu.stream()
			.mapToInt(Dish::getCalories);
		Stream<Integer> stream = intStream.boxed();
		System.out.println(stream);
	}
	// 1~100 사이의 숫자중 짝수의 개수를 출력

	private static void testRangeClosed(){
		long count = IntStream.rangeClosed(1, 100)
			.filter(i -> i % 2 == 0)
			.count();
		System.out.println("1~100 사이의 수중 짝수의 개수 : " + count);
	}
	// 피타고라스 공식을 만족하는 숫자쌍을 탐색
	private static void testPythagoras() {
		List<double[]> result = IntStream.rangeClosed(1, 100).boxed()
			.flatMap(a -> IntStream.rangeClosed(a, 100)
				.mapToObj(b -> new double[] {a, b, Math.sqrt(a * a + b * b)})
				.filter(t->t[2] % 1 == 0))
			.collect(Collectors.toList());
		result.stream()
			.map(Arrays::toString)
			.limit(5)
			.forEach(System.out::println);
	}

	// 단어들을 대문자로 변환한 뒤 출력
	private static void testStreamOf(){
		Stream<String> stream = Stream.of("Moern", "Java", "In", "Action");
		System.out.println("단어들을 대문자로 변환 결과");
		stream.map(String::toUpperCase)
			.forEach(System.out::println);
		System.out.println();
	}

	private static void testStreamOfNullable(){
		List<String> words = Arrays.asList("Moern", null, "In", null);
		List<String> result = words.stream()
			.flatMap(Stream::ofNullable)
			.map(String::toUpperCase)
			.collect(Collectors.toList());
		System.out.println("단어들을 대문자로 변환 결과");
		result.forEach(System.out::println);
		System.out.println();
	}

	// 0 포함 2의 배수를 10개까지 출력
	private static void testIterate(){
		Stream.iterate(0, n -> n + 2)
			.limit(10)
			.forEach(i->System.out.print(i + " "));
		System.out.println();
	}

	private static void testFibonacci(){
		Stream.iterate(new int[]{0, 1}, t->new int[]{t[1], t[0]+t[1]})
			.limit(20)
			.forEach(t->System.out.printf("(%d,%d) ", t[0], t[1]));
		System.out.println();
	}

	// 0~100까지 4의 배수를 0포함해서 출력
	private static void testIteratePredicate() {
		System.out.println("0~100까지 4의 배수를 0 포함해서 출력");
		Stream.iterate(0, n->n<100, n->n+4)
			.forEach(n->System.out.printf("%d ", n));
		System.out.println();
	}

	// 0.0~1.0 사이의 랜덤한 실수를 5개 출력
	private static void testGenerate(){
		System.out.println("0.0~1.0 사이의 랜덤한 실수를 5개 출력");
		Stream.generate(Math::random)
			.limit(5)
			.forEach(i->System.out.printf("%f ", i));
		System.out.println();
	}

	private static void testGenerateForFibonacci(){
		IntSupplier fib = new IntSupplier() {
			private int previous = 0;
			private int current = 1;

			@Override
			public int getAsInt() {
				int oldPrevious = this.previous;
				int nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				return oldPrevious;
			}
		};
		System.out.println("피보나치 수열 10개 출력");
		IntStream.generate(fib)
			// .parallel() // 병렬 실행시 가변 상태로 인해서 쓰레드 세이프 하지 않음
			.limit(10)
				.forEach(i->System.out.printf("%d ", i));
		System.out.println();
	}
}


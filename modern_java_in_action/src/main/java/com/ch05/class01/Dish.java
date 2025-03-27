package com.ch05.class01;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
			new Dish("pork", 800, Dish.Type.MEAT, false),
			new Dish("beef", 700, Dish.Type.MEAT, false),
			new Dish("chicken", 400, Dish.Type.MEAT, false),
			new Dish("french fries", 530, Dish.Type.OTHER, false),
			new Dish("rice", 350, Dish.Type.OTHER, true),
			new Dish("season fruit", 120, Dish.Type.OTHER, true),
			new Dish("pizza", 550, Dish.Type.OTHER, false),
			new Dish("prawns", 400, Dish.Type.FISH, false),
			new Dish("salmon", 450, Dish.Type.FISH, false));

		testFilter(menu);
		testDistinct();
		testTakeWhile(menu);
		testDropWhile(menu);
		testLimit(menu);
		testSkip(menu);
		testMap(menu);
		testFlatMapString();
		testMapForSquares();
		testMapForPair();
		testMapForSumPair();
		testAnyMatch(menu);
		testAllMatch(menu);
		testNoneMatch(menu);
		testFindAny(menu);
		testFindFirst();
		testReduceForSum();
		testReduceForMaxCalories(menu);
		testReduceForMinCalories(menu);
		testReduceForCounting(menu);
	}

	// 메뉴 중에서 채식 요리를 필터링한다
	private static void testFilter(List<Dish> menu) {
		List<Dish> vegetarianMenu = menu.stream()
			.filter(Dish::isVegetarian)
			.collect(Collectors.toList());
		System.out.println("채식 요리 리스트 : " + vegetarianMenu);
		System.out.println();
	}

	// 정수 중에서 짝수인 숫자들을 고유하게 필터링하여 출력
	private static void testDistinct() {
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		List<Integer> even = numbers.stream()
			.filter(i -> i % 2 == 0)
			.distinct()
			.collect(Collectors.toList());
		System.out.println("짝수 리스트 : " + even);
	}
	// 칼로리가 320 미만인 요리 필터링
	private static void testTakeWhile(List<Dish> menu) {
		List<Dish> sliceMenu1 = menu.stream()
			.sorted(Comparator.comparingInt(Dish::getCalories))
			.takeWhile(dish -> dish.getCalories() < 320)
			.collect(Collectors.toList());
		System.out.println("칼로리가 320 미만인 요리 리스트 : " + sliceMenu1);
	}

	// 칼로리가 320 이상인 요리 필터링
	private static void testDropWhile(List<Dish> menus){
		List<Dish> sliceMenu = menus.stream()
			.sorted(Comparator.comparingInt(Dish::getCalories))
			.dropWhile(dish -> dish.getCalories() < 320)
			.collect(Collectors.toList());
		System.out.println("칼로리가 320 이상인 요리 리스트 : " + sliceMenu);
	}

	// 칼로리가 300보다 큰 요리중 최대 3개를 반환
	private static void testLimit(List<Dish> menu) {
		List<Dish> result = menu.stream()
			.filter(dish -> dish.getCalories() > 300)
			.limit(3)
			.collect(Collectors.toList());
		System.out.println("칼로리가 300보다 큰 요리중 최대 3개 목록 : " + result);
	}

	// 칼로리가 300보다 큰 요리중 처음 두 요리를 건너띈 다음에 나머지 요리 반환
	private static void testSkip(List<Dish> menu){
		List<Dish> result = menu.stream()
			.sorted(Comparator.comparingInt(Dish::getCalories))
			.filter(dish -> dish.getCalories() > 300)
			.skip(2)
			.collect(Collectors.toList());
		System.out.println("칼로리가 300보다 큰 요리중 처음 두 요리를 건너띄고 나머지 요리 목록 : " + result);
	}
	// 각 요리를 대상으로 요리의 이름으로 변환하여 리스트 반환

	private static void testMap(List<Dish> menu) {
		List<String> dishNames = menu.stream()
			.map(Dish::getName)
			.collect(Collectors.toList());
		System.out.println("요리 이름 리스트 : " + dishNames);
	}
	// ["Hello", "World"] 문자열 배열을 ["H", "e", "l", "l", "o", "W", "o", "r", "l", "d"] 문자열 리스트로 변환
	private static void testFlatMapString() {
		List<String> words = Arrays.asList("Hello", "World");
		List<String> uniqueCharacters = words.stream()
			.map(word -> word.split(""))
			.flatMap(Arrays::stream)
			.distinct()
			.collect(Collectors.toList());
		System.out.println("단어들을 각각의 단어로 쪼갠 문자열 리스트 : " + uniqueCharacters);
	}

	// 숫자 리스트들을 대상으로 각 숫자의 제곱근으로 이루어진 리스트를 반환
	private static void testMapForSquares(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> squares = numbers.stream()
			.map(n -> n * n)
			.collect(Collectors.toList());
		System.out.println("각 숫자의 제곱근 목록 : " + squares);
	}

	// 두개의 숫자 리스트가 존재할때 모든 숫자 쌍의 리스트를 반환하시오
	private static void testMapForPair(){
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);

		List<int[]> pairs = numbers1.stream()
			.flatMap(n1 -> numbers2.stream()
				.map(n2 -> new int[] {n1, n2}))
			.collect(Collectors.toList());
		System.out.println("두 숫자 리스트의 숫자쌍");
		pairs.stream()
			.forEach(pair -> System.out.print(Arrays.toString(pair) + " "));
		System.out.println();
	}

	// 두 리스트의 숫자쌍중에서 합이 3으로 나누어 떨어지는 쌍만 반환
	private static void testMapForSumPair(){
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		List<int[]> pairs = numbers1.stream()
			.flatMap(n -> numbers2.stream()
				.filter(n2-> (n+n2) % 3 == 0)
				.map(n2 -> new int[] {n, n2}))
			.collect(Collectors.toList());
		System.out.println("숫자 쌍중 합이 3으로 나누어 떨어지는 숫자 쌍 목록");
		pairs.stream()
			.forEach(pair -> System.out.print(Arrays.toString(pair) + " "));
		System.out.println();
	}

	// 적어도 요리들 중에서 채식인 요리가 있는지 여부 확인
	private static void testAnyMatch(List<Dish> menu){
		if(menu.stream().anyMatch(Dish::isVegetarian)){
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}
	}

	// 모든 요리가 1000 칼로리 이하인지 여부 확인
	private static void testAllMatch(List<Dish> menu){
		if(menu.stream().allMatch(dish -> dish.getCalories() <= 1000)){
			System.out.println("All dishes are below 1000 calories");
		}
	}

	// 모든 요리가 1000 칼로리 이상이 아닌지 여부 확인
	private static void testNoneMatch(List<Dish> menu) {
		if (menu.stream().noneMatch(dish->dish.getCalories() >= 1000)){
			System.out.println("All dishes are below 1000 calories");
		}
	}

	// 요리들 중에서 채식 요리중 임의의 하나를 반환한다
	private static void testFindAny(List<Dish> menu) {
		Optional<Dish> dish = menu.stream()
			.filter(Dish::isVegetarian)
			.findAny();
		System.out.println("채식 요리 임의 중 하나 : " + dish.orElse(null));
	}

	// 정수 리스트에서 3으로 나누어 떨어지는 첫번째 제곱값을 반환
	private static void testFindFirst(){
		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
			.map(i -> i * i)
			.filter(i -> i % 3 == 0)
			.findFirst();
		System.out.println("정수 리스트에서 3으로 나누어 떨어지는 첫번째 제곱값 : " + firstSquareDivisibleByThree.orElse(0));
	}

	// 1~10의 합계를 게산
	private static void testReduceForSum(){
		Integer sum = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
			.reduce(0, (a, b) -> a + b);
		System.out.println("1~10의 합계 : " + sum);
	}

	// 요리중에서 칼로리가 가장 높은 요리를 탐색합니다.
	private static void testReduceForMaxCalories(List<Dish> menu) {
		Dish mostCalorieDish = menu.stream()
			.reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
			.orElse(null);
		System.out.println("칼로리가 가장 높은 요리 : " + mostCalorieDish);
	}

	// 요리중에서 칼로리가 가장 낮은 요리를 탐색
	private static void testReduceForMinCalories(List<Dish> menu) {
		Dish leastCalorieDish = menu.stream()
			.reduce((d1, d2) -> d1.getCalories() < d2.getCalories() ? d1 : d2)
			.orElse(null);
		System.out.println("칼로리가 가장 낮은 요리 : " + leastCalorieDish);
	}

	// 메뉴의 요리 개수를 반환
	private static void testReduceForCounting(List<Dish> menu) {
		Integer counting = menu.stream()
			.map(dish -> 1)
			.reduce(0, Integer::sum);
		System.out.println("요리의 개수 : " + counting);
	}
}


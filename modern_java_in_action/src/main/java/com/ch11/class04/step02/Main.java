package com.ch11.class04.step02;

import java.util.Optional;
import java.util.OptionalInt;

public class Main {
	public static void main(String[] args) {
		// Optional<Person> 객체 생성
		Optional<Person> person = Optional.of(new Person("John Doe", 30));

		// Optional<Person>에서 age를 가져오는 메서드 참조
		Optional<OptionalInt> optionalAge = person.map(Person::getAgeOptional);

		// 문제: OptionalInt를 Optional의 flatMap에 메서드 참조로 전달할 수 없음
		// 컴파일 에러 발생
		// Optional<Integer> flatMappedAge = person.flatMap(Person::getAgeOptional);

		person.flatMap(Person::getNameOptional)
			.ifPresent(System.out::println);

		// 해결: OptionalInt를 Optional로 변환
		Optional<Integer> flatMappedAge = person.flatMap(p ->
			p.getAgeOptional().isPresent() ? Optional.of(p.getAgeOptional().getAsInt()) : Optional.empty()
		);

		// 결과 출력
		flatMappedAge.ifPresent(age -> System.out.println("Age: " + age));
	}
}

class Person{
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Optional<String> getNameOptional(){
		return Optional.ofNullable(name);
	}

	public OptionalInt getAgeOptional() {
		return OptionalInt.of(age);
	}
}

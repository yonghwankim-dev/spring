package com.ch06.class05.step01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	/**
	 * The supplier method is a function that returns a supplier with an empty result.
	 * @return Supplier<List<T>>
	 */
	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	/**
	 * The accumulator method returns a function that performs a reduction operation
	 * @return BiConsumer<List<T>, T>
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	/**
	 * The combiner defines how the accumulator will handle this result
	 * when processing different sub-parts of the stream in parallel
	 * @return BinaryOperator<List<T>>
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2)->{
			list1.addAll(list2);
			return list1;
		};
	}

	/**
	 * The finisher method is a function called at the end of
	 * the stream search and the end of the accumulation process
	 * while converting the accumulator object to the final result
	 * @return Function<List<T>, List<T>>
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
	}
}

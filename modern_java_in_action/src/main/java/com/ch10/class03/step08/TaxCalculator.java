package com.ch10.class03.step08;

import java.util.function.DoubleUnaryOperator;

public class TaxCalculator {
	public DoubleUnaryOperator taxFunction = d->d;

	public TaxCalculator with(DoubleUnaryOperator f){
		taxFunction = taxFunction.andThen(f);
		return this;
	}

	public double calculate(Order order){
		return taxFunction.applyAsDouble(order.getValue());
	}
}

package com.ch10.class03.step07;

import static com.ch10.class03.step07.Tax.*;

public class TaxCalculator {
	private boolean useRegional;
	private boolean useGeneral;
	private boolean useSurcharge;

	public TaxCalculator withTaxRegional(){
		useRegional = true;
		return this;
	}

	public TaxCalculator withTaxGeneral(){
		useGeneral = true;
		return this;
	}

	public TaxCalculator withTaxSurcharge(){
		useSurcharge = true;
		return this;
	}

	public double calculate(Order order){
		return calculate(order, useRegional, useGeneral, useSurcharge);
	}

	private double calculate(Order order, boolean useRegional, boolean useGeneral, boolean useSurcharge) {
		double value = order.getValue();
		if (useRegional){
			value = regional(value);
		}
		if (useGeneral){
			value = general(value);
		}
		if (useSurcharge){
			value = surcharge(value);
		}
		return value;
	}
}

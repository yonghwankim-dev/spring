package main;

import chap07.ExeTimeCalculator;
import chap07.ImpleCalculator;
import chap07.RecCalculator;

public class MainProxy {
	public static void main(String args[])
	{
		ExeTimeCalculator tlCal1 = new ExeTimeCalculator(new ImpleCalculator());
		System.out.println(tlCal1.factorial(20));
		
		ExeTimeCalculator tlCal2 = new ExeTimeCalculator(new RecCalculator());
		System.out.println(tlCal2.factorial(20));
	}
}

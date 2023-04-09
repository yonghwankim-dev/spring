package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import chap07.RecCalculator;
import config.AppCtx;

public class MainAspect {
	public static void main(String args[])
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		// 수정 전
//		Calculator cal = ctx.getBean("calculator", Calculator.class);
		
		// 수정 후(import에도 RecCalculator 추가)
		RecCalculator cal = ctx.getBean("calculator",RecCalculator.class);
		
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		
		System.out.println(cal.getClass().getName());
		ctx.close();
	}
}

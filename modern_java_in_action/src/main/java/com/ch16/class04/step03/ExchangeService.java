package com.ch16.class04.step03;


public class ExchangeService {

	public static final double DEFAULT_RATE = 1.35;

	public enum Money{
		USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);

		private final double rate;

		Money(double rate) {
			this.rate = rate;
		}
	}

	public double getRate(Money src, Money dst){
		return getRateWithDelay(src, dst);
	}

	private static double getRateWithDelay(Money source, Money destination) {
		delay();
		return destination.rate / source.rate;
	}

	public static void delay(){
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}

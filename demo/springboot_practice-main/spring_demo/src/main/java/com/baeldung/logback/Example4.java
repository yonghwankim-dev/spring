package com.baeldung.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example4 {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Example4.class);

		String message = "This is a String";
		Integer zero = 0;

		try {
			logger.debug("Logging message: {}", message);
			logger.debug("Going to divide {} by {}", 42, zero);
			int result = 42 / zero;
		} catch (Exception e) {
			logger.error("Error dividing {} by {} ", 42, zero, e);
		}
	}
}

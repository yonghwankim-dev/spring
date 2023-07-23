package com.baeldung.logback.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example6 {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Example6.class);
		logger.warn("예제 6 경고 로그 메시지");
	}
}

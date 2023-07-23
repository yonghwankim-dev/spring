package com.baeldung.logback.test2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example7 {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Example7.class);
		logger.info("예제 7 정보 로그 메시지");
	}
}

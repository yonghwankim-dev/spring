package com.baeldung.logback;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class Example5 {
	public static void main(String[] args) {
		Logger foobar =
			(ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.baeldung.foobar");
		Logger logger =
			(ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.baeldung.logback");
		Logger testslogger =
			(ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.baeldung.logback.tests");

		foobar.debug("This is logged from foobar");
		logger.debug("This is not logged from logger");
		logger.info("This is logged from logger");
		testslogger.info("This is not logged from tests");
		testslogger.warn("This is logged from tests");
	}
}

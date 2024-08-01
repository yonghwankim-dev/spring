package com.ch09.class01.step06;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("logger");
		if (logger.isLoggable(Level.FINER)){
			logger.finer("Problem: " + generateDiagnostic());
		}
		logger.log(Level.FINER, "Problem: " + generateDiagnostic());
		logger.log(Level.FINER, ()->"Problem: " + generateDiagnostic());
	}

	private static String generateDiagnostic() {
		return "Diagnostic";
	}
}

package com.ch09.class01.step07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

public class Main {
	interface BufferedReaderProcessor {
		String process(BufferedReader b) throws IOException;
	}

	public static void main(String[] args) {
		BufferedReaderProcessor oneLine = BufferedReader::readLine;
		BufferedReaderProcessor twoLine = b -> b.readLine() + b.readLine();
		System.out.println(processFile(oneLine));
		System.out.println(processFile(twoLine));
	}

	private static String processFile(BufferedReaderProcessor processor) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("data.txt").getInputStream()))) {
			return processor.process(br);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

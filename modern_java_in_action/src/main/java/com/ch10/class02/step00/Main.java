package com.ch10.class02.step00;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;

public class Main {
	public static void main(String[] args) throws IOException {
		test1();
		test2();
	}

	private static void test1() throws IOException {
		String filename = "input.txt";
		List<String> errors = new ArrayList<>();
		int errorCount = 0;
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		while (errorCount < 40 && line != null) {
			if (line.startsWith("ERROR")) {
				errors.add(line);
				errorCount++;
			}
			line = br.readLine();
		}
	}

	private static void test2() throws IOException {
		Path filename = Path.of(new ClassPathResource("input.txt").getPath());
		List<String> errors = Files.lines(filename)
			.filter(line -> line.startsWith("ERROR"))
			.limit(40)
			.collect(Collectors.toList());
		System.out.println(errors);
	}
}

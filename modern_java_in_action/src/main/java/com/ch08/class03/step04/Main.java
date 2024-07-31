package com.ch08.class03.step04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

public class Main {
	private MessageDigest messageDigest;

	public Main() throws NoSuchAlgorithmException {
		this.messageDigest = MessageDigest.getInstance("SHA-256");
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		Main main = new Main();
		main.computeIfAbsentExample();
	}

	private void computeIfAbsentExample() throws IOException {
		Map<String, byte[]> dataToHash = new HashMap<>();
		ClassPathResource resource = new ClassPathResource("data.txt");
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
			reader.lines().forEach(line ->{
				dataToHash.computeIfAbsent(line, this::calculateDigest);
			});
		}
		System.out.println(dataToHash);
	}

	private byte[] calculateDigest(String key){
		return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
	}
}

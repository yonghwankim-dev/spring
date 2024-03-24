package com.ch17.class01.step03;

import java.util.Random;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class TempInfo {
	public static final Random random = new Random();

	private final String town;
	private final int temp;

	public static TempInfo fetch(String town){
		return new TempInfo(town, random.nextInt(100)); // 0~99 사이의 임의의 화씨온도 반환
	}
}

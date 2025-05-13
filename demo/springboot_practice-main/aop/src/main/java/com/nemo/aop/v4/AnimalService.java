package com.nemo.aop.v4;

public class AnimalService {
	public String getAnimalSound(Animal animal) {
		return animal.saySound();
	}
}

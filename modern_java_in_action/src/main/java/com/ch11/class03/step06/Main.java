package com.ch11.class03.step06;

import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		Insurance insurance = new Insurance("CambridgeInsurance");
		if (insurance != null && "CambridgeInsurance".equals(insurance.getName())){
			System.out.println("ok");
		}

		Optional<Insurance> optInsurance = Optional.of(new Insurance("CambridgeInsurance"));
		optInsurance.filter(ins -> "CambridgeInsurance".equals(ins.getName()))
			.ifPresent(ins->System.out.println("ok"));
	}
}

class Insurance{
	private String name;

	public Insurance(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

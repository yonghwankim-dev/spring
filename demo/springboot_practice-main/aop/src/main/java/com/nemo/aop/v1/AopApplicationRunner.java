package com.nemo.aop.v1;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AopApplicationRunner implements ApplicationRunner {

	private final UserServiceImpl userService;

	public AopApplicationRunner(UserServiceImpl userService) {
		this.userService = userService;
	}

	@Override
	public void run(ApplicationArguments args) {
		userService.add(new User("Dave", Level.BASIC));
		userService.upgradeLevels();
		userService.printAllUsers();
	}
}

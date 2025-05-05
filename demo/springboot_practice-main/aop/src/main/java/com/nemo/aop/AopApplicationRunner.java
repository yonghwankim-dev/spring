package com.nemo.aop;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.nemo.aop.user.domain.Level;
import com.nemo.aop.user.domain.User;
import com.nemo.aop.user.service.UserService;

public class AopApplicationRunner implements ApplicationRunner {

	private final UserService userService;

	public AopApplicationRunner(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void run(ApplicationArguments args) {
		userService.add(new User("Dave", Level.BASIC));
		userService.upgradeLevels();
		userService.printAllUsers();
	}
}

package com.nemo.aop;

import java.lang.reflect.Proxy;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.nemo.aop.hello.Hello;
import com.nemo.aop.hello.HelloTarget;
import com.nemo.aop.hello.UppercaseHandler;
import com.nemo.aop.user.domain.Level;
import com.nemo.aop.user.domain.User;
import com.nemo.aop.user.service.UserService;

public class AopApplicationRunner implements ApplicationRunner {

	private final UserService userService;
	private final Hello hello;

	public AopApplicationRunner(UserService userService, Hello hello) {
		this.userService = userService;
		this.hello = hello;
	}

	@Override
	public void run(ApplicationArguments args) {
		userService.add(new User("Dave", Level.BASIC));
		userService.upgradeLevels();
		userService.printAllUsers();

		System.out.println(hello.sayHello("Dave"));
		Hello proxiedHello = (Hello)Proxy.newProxyInstance(getClass().getClassLoader(),
			new Class[] {Hello.class},
			new UppercaseHandler(new HelloTarget()));
		System.out.println(proxiedHello.sayHello("Dave"));
	}
}

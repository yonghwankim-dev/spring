package com.nemo.aop.user.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.nemo.aop.user.dao.MockUserDao;
import com.nemo.aop.user.domain.Level;
import com.nemo.aop.user.domain.User;

class UserServiceImplTest {

	private UserService userService;
	private MockUserDao userDao;

	@BeforeEach
	void setUp() {
		List<User> users = List.of(
			new User(1L, "Alice", Level.BASIC),
			new User(2L, "Bob", Level.SILVER),
			new User(3L, "Charlie", Level.GOLD)
		);
		userDao = new MockUserDao(users);
		userService = new UserServiceImpl(userDao);
	}

	@DisplayName("사용자들의 레벨을 업그레이드한다")
	@Test
	void upgradeLevels() {
		// given

		// when
		userService.upgradeLevels();
		// then
		List<User> actual = userDao.getUpdated();
		Assertions.assertThat(actual).hasSize(2);
	}
}

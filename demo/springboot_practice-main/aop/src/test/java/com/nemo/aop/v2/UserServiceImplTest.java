package com.nemo.aop.v2;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

	@DisplayName("업그레이드된 사용자 수를 검증한다")
	@Test
	void verifyUpgradeCount() {
		// given
		UserDao userDao = Mockito.mock(UserDao.class);
		Mockito.when(userDao.getAll()).thenReturn(List.of(
			new User(1L, "Alice", Level.BASIC),
			new User(2L, "Bob", Level.SILVER),
			new User(3L, "Charlie", Level.GOLD)
		));
		UserService userService = new UserServiceImpl(userDao);
		// when
		userService.upgradeLevels();
		// then
		Mockito.verify(userDao, Mockito.times(2)).upgradeLevel(Mockito.any());
	}
}

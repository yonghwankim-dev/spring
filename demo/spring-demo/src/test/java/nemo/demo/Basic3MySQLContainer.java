package nemo.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Testcontainers
@DisplayName("@Container 어노테이션 없이 가장 기본 사용 방법, 직접 start(), stop()으로 매 테스트마다 도커 띄우기")
class Basic3MySQLContainer {

	@Container
	private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.24");

	@Test
	void test1() {
		log.info("로그 getJdbcDriverInstance {} ", mySQLContainer.getJdbcDriverInstance());
		log.info("로그 getJdbcUrl {} ", mySQLContainer.getJdbcUrl());
		log.info("로그 getMappedPort {} ", mySQLContainer.getMappedPort(3306));
		log.info("로그 getHost {} ", mySQLContainer.getHost());
		log.info("로그 getUsername {} ", mySQLContainer.getUsername());
		log.info("로그 getPassword {} ", mySQLContainer.getPassword());
	}

	@Test
	void test2() {
		log.info("로그 getJdbcDriverInstance {} ", mySQLContainer.getJdbcDriverInstance());
		log.info("로그 getJdbcUrl {} ", mySQLContainer.getJdbcUrl());
		log.info("로그 getMappedPort {} ", mySQLContainer.getMappedPort(3306));
		log.info("로그 getHost {} ", mySQLContainer.getHost());
		log.info("로그 getUsername {} ", mySQLContainer.getUsername());
		log.info("로그 getPassword {} ", mySQLContainer.getPassword());
	}
}

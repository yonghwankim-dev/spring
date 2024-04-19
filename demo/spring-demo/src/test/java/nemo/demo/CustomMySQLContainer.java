package nemo.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Testcontainers
@DisplayName("MySQL 컨테이너 커스텀 설정")
class CustomMySQLContainer {

	@Container
	static JdbcDatabaseContainer mySQLContainer = new MySQLContainer("mysql:8.0.24")
		.withConfigurationOverride("learning.testcontainers/custom.conf.d")
		.withDatabaseName("customdb")
		.withUsername("kukim")
		.withPassword("1234")
		.withInitScript("learning.testcontainers/init.sql");

	@Test
	void test1() {
		log.info("test 1 로그 getJdbcDriverInstance {} ", mySQLContainer.getJdbcDriverInstance());
		log.info("test 1 로그 getJdbcUrl {} ", mySQLContainer.getJdbcUrl());
		log.info("test 1 로그 getMappedPort {} ", mySQLContainer.getMappedPort(3306));
		log.info("test 1 로그 getHost {} ", mySQLContainer.getHost());
		log.info("test 1 로그 getUsername {} ", mySQLContainer.getUsername());
		log.info("test 1 로그 getPassword {} ", mySQLContainer.getPassword());
	}
}

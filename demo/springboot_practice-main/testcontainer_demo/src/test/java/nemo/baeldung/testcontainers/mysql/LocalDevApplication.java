package nemo.baeldung.testcontainers.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;

import nemo.baeldung.testcontainers.Application;


public class LocalDevApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main)
			.with(LocalDevTestcontainersConfig.class)
			.run(args);
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class LocalDevTestcontainersConfig{

		@Bean
		@ServiceConnection
		@RestartScope
		public MySQLContainer mySQLContainer() {
			return new MySQLContainer("mysql:8.0.33")
				.withUsername("admin")
				.withPassword("password1234!")
				.withDatabaseName("testdb");
		}
	}
}

package nemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.restassured.RestAssured;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class AbstractContainerBaseTest {
	private static final String REDIS_IMAGE = "redis:7-alpine";
	private static final int REDIS_PORT = 6379;

	private static final GenericContainer REDIS_CONTAINER = new GenericContainer(REDIS_IMAGE)
		.withExposedPorts(REDIS_PORT)
		.withReuse(true);

	static {
		REDIS_CONTAINER.start();
	}

	@LocalServerPort
	protected int port;

	@DynamicPropertySource
	public static void overrideProps(DynamicPropertyRegistry registry) {
		// redis property config
		registry.add("spring.redis.host", REDIS_CONTAINER::getHost);
		registry.add("spring.redis.port", () -> REDIS_CONTAINER.getMappedPort(REDIS_PORT).toString());

		// mysql property config
		registry.add("spring.datasource.driver-class-name", () -> "org.testcontainers.jdbc.ContainerDatabaseDriver");
		registry.add("spring.datasource.url", () -> "jdbc:tc:mysql:8.0.33://localhost/testdb");
		registry.add("spring.datasource.username", () -> "admin");
		registry.add("spring.datasource.password", () -> "password1234!");
	}

	@BeforeEach
	void setup(){
		RestAssured.port = port;
	}
}

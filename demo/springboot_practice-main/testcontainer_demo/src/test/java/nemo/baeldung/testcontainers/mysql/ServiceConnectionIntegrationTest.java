package nemo.baeldung.testcontainers.mysql;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import nemo.baeldung.testcontainers.mongo.support.MiddleEarthCharacter;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ServiceConnectionIntegrationTest {

	@Container
	@ServiceConnection
	private static final MySQLContainer MYSQL_CONTAINER = new MySQLContainer("mysql:8.0.33")
		.withUsername("admin")
		.withPassword("password1234!")
		.withDatabaseName("testdb");
}

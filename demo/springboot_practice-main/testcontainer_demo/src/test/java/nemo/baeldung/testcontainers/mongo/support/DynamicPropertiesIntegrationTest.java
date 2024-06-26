package nemo.baeldung.testcontainers.mongo.support;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class DynamicPropertiesIntegrationTest {

	@Container
	private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Autowired
	private MiddleEarthCharactersRepository repository;

	@BeforeEach
	void beforeEach() {
		repository.deleteAll();
	}

	@Test
	void whenRequestingHobbits_thenReturnFrodoAndSam(){
	    // given
		repository.saveAll(List.of(
			new MiddleEarthCharacter("Frodo", "hobbit"),
			new MiddleEarthCharacter("Samwise", "hobbit"),
			new MiddleEarthCharacter("Aragon", "human"),
			new MiddleEarthCharacter("Gandalf", "wizard")
		));

	    // when & then
		when().get("/api/characters?race=hobbit")
			.then().statusCode(200)
			.and().body("name", hasItems("Frodo", "Samwise"));
	}
}

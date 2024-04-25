package nemo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberIntegrationTest extends AbstractContainerBaseTest{

	@Autowired
	private MemberRepository repository;

	@BeforeEach
	void clean(){
		repository.deleteAllInBatch();
	}

	@AfterEach
	void tearDown(){
		repository.deleteAllInBatch();
	}

	@DisplayName("회원들을 조회한다")
	@Test
	void whenRequestMembers_thenReturnMembers(){
		repository.saveAll(List.of(
			new Member("kim"),
			new Member("lee"),
			new Member("park")
		));

		when().get("/members")
			.then().statusCode(200)
			.and().body("name", hasItems("kim", "lee", "park"));
	}

}

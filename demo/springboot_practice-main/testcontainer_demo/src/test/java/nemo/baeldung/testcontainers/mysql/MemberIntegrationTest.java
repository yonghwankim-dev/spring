package nemo.baeldung.testcontainers.mysql;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import nemo.baeldung.testcontainers.member.Member;
import nemo.baeldung.testcontainers.member.MemberRepository;

public class MemberIntegrationTest extends ServiceConnectionIntegrationTest{

	@Autowired
	private MemberRepository repository;

	@Test
	void findAllTest(){
	    // given
		repository.saveAll(
			List.of(
				new Member(null, "Frodo"),
				new Member(null, "Samwise")
			)
		);
	    // when & then
		when().get("/members")
			.then().statusCode(200)
			.and().body("name", hasItems("Frodo", "Samwise"));
	}
}

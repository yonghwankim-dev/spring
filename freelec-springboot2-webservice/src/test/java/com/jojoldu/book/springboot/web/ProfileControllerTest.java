package com.jojoldu.book.springboot.web;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.env.MockEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProfileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("real profile이 조회되다")
    public void test1() {
        // given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);
        // when
        String profile = controller.profile();
        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    @DisplayName("real profile이 없으면 첫번째가 조회된다.")
    public void test2() {
        // given
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);
        // when
        String profile = controller.profile();
        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    @DisplayName("active profile이 없으면 default가 조회된다.")
    public void test3() {
        // given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);
        // when
        String profile = controller.profile();
        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    @DisplayName("profile은 인증없이 호출된다")
    public void test4() {
        // given
        String expected = "default";
        // when
        ResponseEntity<String> response = restTemplate.getForEntity("/profile", String.class);
        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }
}

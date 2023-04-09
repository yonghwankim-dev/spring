package kr.yh.spring_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;

@WebFluxTest(SampleController.class)
public class SampleControllerWebFluxTest {
    @MockBean
    SampleService mockSampleService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testHello(){
        when(mockSampleService.getName()).thenReturn("yonghwan");

        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello yonghwan");
    }
}

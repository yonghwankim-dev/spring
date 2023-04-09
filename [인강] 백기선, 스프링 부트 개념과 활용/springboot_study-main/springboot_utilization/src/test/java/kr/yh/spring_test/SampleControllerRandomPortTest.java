package kr.yh.spring_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerRandomPortTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    SampleService mockSampleService;

    @Autowired
    WebTestClient webTestClient;

    // TestRestTemplate 사용
    @Test
    public void testHello() throws Exception {
        String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello yonghwan");
    }

    @Test
    public void testHelloUsingMockBean(){
        when(mockSampleService.getName()).thenReturn("yonghwan");

        String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello yonghwan");
    }

    @Test
    public void testHello_webTestClient(){
        when(mockSampleService.getName()).thenReturn("yonghwan");

        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello yonghwan");
    }
}
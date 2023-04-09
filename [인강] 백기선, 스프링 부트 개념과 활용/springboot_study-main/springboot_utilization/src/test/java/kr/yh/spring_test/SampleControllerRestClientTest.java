package kr.yh.spring_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest
public class SampleControllerRestClientTest {

    RestTemplate restTemplate;

    MockRestServiceServer server;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws JsonProcessingException {
        restTemplate = new RestTemplateBuilder().build();
        server = MockRestServiceServer.createServer(restTemplate);

        String sampleString = objectMapper.writeValueAsString(new Sample(null, "yonghwan", 20));

        server.expect(MockRestRequestMatchers.requestTo("/yonghwan/details"))
                .andRespond(MockRestResponseCreators.withSuccess(sampleString, MediaType.APPLICATION_JSON));
    }


    @Test
    public void test() {
        Sample sample = restTemplate.getForObject("/{name}/details", Sample.class, "yonghwan");

        assertThat(sample.getName()).isEqualTo("yonghwan");
        assertThat(sample.getAge()).isEqualTo(20);
    }
}

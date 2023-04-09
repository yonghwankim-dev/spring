package kr.yh.spring_test;

import kr.yh.spring_test.json_test.UserDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JsonTest
public class SampleControllerJsonTest {
    @Autowired
    private JacksonTester<UserDetails> json;

    @Test
    public void testSerialize() throws IOException {
        UserDetails userDetails =
                new UserDetails(1L, "Duke", "Java",
                        LocalDate.of(1995, 1, 1), true);

        JsonContent<UserDetails> result = this.json.write(userDetails);
        assertThat(result).hasJsonPathStringValue("$.firstname");
        assertThat(result).extractingJsonPathStringValue("$.firstname").isEqualTo("Duke");
        assertThat(result).extractingJsonPathStringValue("$.lastname").isEqualTo("Java");
        assertThat(result).extractingJsonPathStringValue("$.dateofbirth").isEqualTo("1995.01.01");
        assertThat(result).doesNotHaveJsonPath("$.enabled");
    }

    @Test
    public void testDeserialize() throws IOException {

        String jsonContent = "{\"firstname\":\"Mike\", \"lastname\": \"Meyer\"," +
                " \"dateofbirth\":\"1990.05.15\"," +
                " \"id\": 42, \"enabled\": true}";

        UserDetails result = this.json.parse(jsonContent).getObject();

        assertThat(result.getFirstName()).isEqualTo("Mike");
        assertThat(result.getLastName()).isEqualTo("Meyer");
        assertThat(result.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 05, 15));
        assertThat(result.getId()).isEqualTo(42L);
        assertThat(result.isEnabled()).isEqualTo(true);
    }

}
package kr.yh;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({EventFormatter.class, EventController.class})
public class EventControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getTest() throws Exception {
        //given
        String url = "/event/";
        String id  = "1";
        //when
        String actual = mockMvc.perform(get(url+id))
                .andExpect(status().isOk())
                .andExpect(content().string(id))
                .andReturn().getResponse().getContentAsString();
        //then
        assertThat(actual).isEqualTo("1");
    }
}
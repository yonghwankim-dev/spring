package kr.yh.hateoas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HateoasSampleController.class)
public class SampleControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testHello() throws Exception {
        //given
        String url = "/hateoas";
        //when
        this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self").exists())
                .andDo(print());
        //then
    }
}
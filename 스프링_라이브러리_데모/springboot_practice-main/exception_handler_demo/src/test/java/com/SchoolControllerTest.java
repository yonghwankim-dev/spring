package com;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class SchoolControllerTest {
    @Autowired
    MockMvc mockMvc;

    @DisplayName("/school/exception1 url 호출시 전역 컨트롤러 예외 핸들링이 작동되는지 테스트")
    @Test
    public void testException1() throws Exception {
        //given
        String url = "/school/exception1";
        //when
        String actual = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
        //then
        Assertions.assertThat(actual).isEqualTo("exception handle!");
    }
}
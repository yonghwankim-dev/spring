package com;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.ModelMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("/person/exception url 요청시 널포인터가 핸들링 되는지 테스트")
    @Test
    public void testException1() throws Exception {
        //given
        String url = "/person/exception";
        //when
        String actual = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        //then
        Assertions.assertThat(actual).isEqualTo("nullPointerException Handle!!");
    }

    @DisplayName("/person/exception2 url 요청시 예외 핸들링이 되는지 테스트")
    @Test
    public void testException2() throws Exception {
        //given
        String url = "/person/exception2";
        //when
        String actual = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        //then
        Assertions.assertThat(actual).isEqualTo("exception handle!!");
    }

    @DisplayName("/person/exception3 url 요청시 예외 핸들링이 되는지 테스트")
    @Test
    public void testException3() throws Exception {
        //given
        String url = "/person/exception3";
        //when
        String actual = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        //then
        Assertions.assertThat(actual).isEqualTo("exception handle!!");
    }

    @DisplayName("/person/register url 요청시 문자열 등록날짜가 localdate의 포맷으로 변환되는지 테스트")
    @Test
    public void testRegister() throws Exception {
        //given
        String url = "/person/register";
        //when
        String actual = this.mockMvc.perform(MockMvcRequestBuilders.get(url)
                                        .param("id", "1")
                                        .param("name", "yonghwan")
                                        .param("registerDate", "2022-12-13"))
                                    .andExpect(status().isOk())
                                    .andReturn().getResponse().getContentAsString();
        //then
        Assertions.assertThat(actual).isEqualTo("Person(id=1, name=yonghwan, registerDate=2022-12-13)");
    }

    @DisplayName("PersonController 내에서 공통으로 사용하는 모델인 msg가 잘 저장되었는지 테스트")
    @Test
    public void testAddAttributes() throws Exception {
        //given
        String url = "/person/page";
        //when
        ModelMap modelMap = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn().getModelAndView().getModelMap();
        String actual = (String) modelMap.getAttribute("msg");
        //then
        Assertions.assertThat(actual).isEqualTo("hello world");
    }

}
package com;

import com.controller.LoginController;
import com.domain.Member;
import com.service.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LoginService loginService;

    @Test
    public void 아이디_패스워드_전달_테스트() throws Exception {
        //given
        String userid = "user1";
        String password = "user1";
        Member member = Member.builder().name("홍길동").userid(userid).password(password).build();
        //mocking
        when(loginService.login(userid, password)).thenReturn(member);
        //when
        mockMvc.perform(post("/login")
                .param("userid", userid)
                .param("password", password))
                .andExpect(status().isOk())
                .andDo(print());
        //then
    }
}
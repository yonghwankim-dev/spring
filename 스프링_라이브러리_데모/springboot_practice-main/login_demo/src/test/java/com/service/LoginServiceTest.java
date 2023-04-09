package com.service;

import com.domain.Member;
import com.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("로그인이 성공하는 테스트")
    public void login_success(){
        //given
        String userid = "user1";
        String password = "user1";
        Member member = Member.builder().name("홍길동").userid(userid).password(password).build();
        //mocking
        when(memberRepository.findByUserid(userid)).thenReturn(Optional.ofNullable(member));
        //when
        Member actual = loginService.login(userid, password);
        //then
        Assertions.assertThat(actual).isEqualTo(member);
    }
}
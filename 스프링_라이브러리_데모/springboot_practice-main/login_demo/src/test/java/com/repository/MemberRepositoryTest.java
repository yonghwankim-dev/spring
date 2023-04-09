package com.repository;

import com.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원이 초기화 되었는지 테스트")
    public void findAll(){
        //given

        //when
        int actual = memberRepository.findAll().size();
        //then
        assertThat(actual).isGreaterThan(0);
    }

    @Test
    public void findByUserId(){
        //given
        String userid = "user1";
        String password = "user1";
        //when
        Member member = memberRepository.findByUserid(userid).orElse(null);
        //then
        assertThat(member).isNotNull();
        assertThat(member.equalUseridAndPassword(userid, password)).isTrue();
    }

}
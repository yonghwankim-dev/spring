package kr.yh.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = {"local"})
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void testFindAll(){
        //given

        //when
        List<Member> members = memberRepository.findAll();
        //then
        assertThat(members.size()).isGreaterThan(0);
    }
}
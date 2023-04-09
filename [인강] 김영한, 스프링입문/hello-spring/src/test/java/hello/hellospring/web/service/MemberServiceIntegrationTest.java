package hello.hellospring.web.service;

import hello.hellospring.domain.Member;
import hello.hellospring.web.repository.MemberRepository;
import hello.hellospring.web.repository.MemoryMemberRepository;
import java.sql.SQLException;
import java.util.List;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입")
    void join() throws SQLException {
        //given
        Member member = new Member();
        member.setName("김용환");
        //when
        Long id = memberService.join(member);
        //then
        Member actual = memberService.fineOne(id).get();
        Assertions.assertThat(actual).isEqualTo(member);
    }

    @Test
    @DisplayName("회원가입 중복 회원 예외")
    public void join_fail() throws SQLException {
        //given
        Member member1 = new Member();
        member1.setName("김용환");

        Member member2 = new Member();
        member2.setName("김용환");

        //when
        memberService.join(member1);
        //then
        Assertions.assertThatThrownBy(() -> memberService.join(member2))
            .isInstanceOf(IllegalArgumentException.class).hasMessage("이미 존재하는 회원입니다.");
    }

    @Test
    @DisplayName("회원 전체 조회")
    void findMembers() throws SQLException {
        //given
        Member member = new Member();
        member.setName("김용환");
        memberService.join(member);
        //when
        List<Member> members = memberService.findMembers();
        //then
        Assertions.assertThat(members.size()).isEqualTo(1);
    }
}

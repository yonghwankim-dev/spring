package hello.core.member;

import static org.junit.jupiter.api.Assertions.*;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    private AppConfig appConfig = new AppConfig();

    private MemberService memberService = appConfig.memberService();

    @Test
    public void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}

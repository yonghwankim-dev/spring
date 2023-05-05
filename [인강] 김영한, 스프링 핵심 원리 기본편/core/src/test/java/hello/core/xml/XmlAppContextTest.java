package hello.core.xml;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContextTest {

    @Test
    public void xmlAppContext() {
        //given
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
            "appConfig.xml");
        //when
        MemberService memberService = ctx.getBean("memberService", MemberService.class);
        OrderService orderService = ctx.getBean("orderService", OrderService.class);
        MemberRepository memberRepository = ctx.getBean("memberRepository", MemberRepository.class);
        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
}

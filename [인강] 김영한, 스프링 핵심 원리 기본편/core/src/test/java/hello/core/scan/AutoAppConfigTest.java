package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    public void basicScan() {
        //given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            AutoAppConfig.class);
        //when
        MemberService memberService = ctx.getBean(MemberService.class);
        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

}

package hello.core.beanfind;

import static org.assertj.core.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
        AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    public void findBeanByName() {
        //given
        String beanName = "memberService";
        //when
        MemberService memberService = ctx.getBean(beanName, MemberService.class);
        //then
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    public void findBeanByType() {
        //given
        Class<MemberService> beanType = MemberService.class;
        //when
        MemberService memberService = ctx.getBean(beanType);
        //then
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("빈 구체 타입으로 조회")
    public void findBeanByImplementedType() {
        //given
        Class<MemberServiceImpl> beanType = MemberServiceImpl.class;
        //when
        MemberService memberService = ctx.getBean(beanType);
        //then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 x")
    public void findBeanByName_fail() {
        //given
        String beanName = "xxx";
        //when
        Throwable throwable = catchThrowable(() -> ctx.getBean(beanName, MemberService.class));
        //then
        Assertions.assertThat(throwable).isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}

package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링이 없는 순수한 DI 컨테이너")
    public void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회 : 호출할때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 3. 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
        //given

        //when
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        //then
        System.out.println(singletonService1);
        System.out.println(singletonService2);
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

        // same : 물리적인 주소 비교
        // equal : 내용물을 비교
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    public void springContainerAndSingleton() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            AppConfig.class);
        // 1. 조회 : 호출할때마다 객체를 생성
        MemberService memberService1 = ctx.getBean(MemberService.class);
        // 2. 조회 : 호출할때마다 객체를 생성
        MemberService memberService2 = ctx.getBean(MemberService.class);

        // 3. 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

    @Test
    public void beansingleton() {
        //given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            AppConfig.class);
        MemberServiceImpl memberService = ctx.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ctx.getBean("orderService", OrderServiceImpl.class);
        //when
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        MemberRepository memberRepository3 = ctx.getBean("memberRepository",
            MemberRepository.class);
        //then
        System.out.println("memberService.getMemberRepository() = " + memberRepository1);
        System.out.println("orderService.getMemberRepository() = " + memberRepository2);
        System.out.println("ctx.getMemberRepository() = " + memberRepository3);
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
        Assertions.assertThat(memberRepository2).isSameAs(memberRepository3);

    }

    @Test
    public void configurationDeep() {
        //given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            AppConfig.class);
        //when
        AppConfig appConfig = ctx.getBean(AppConfig.class);
        System.out.println(appConfig.getClass());
        //then
    }

}

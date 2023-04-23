package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // 스프링 컨테이너에 AppConfig에 정의된 빈을 넣습니다.
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ctx.getBean("memberService", MemberService.class);
        OrderService orderService = ctx.getBean("orderService", OrderService.class);

        Member member = new Member(1L, "김용환", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

        Order order = orderService.createOrder(1L, "item1", 1000);
        System.out.println(order);
    }

}

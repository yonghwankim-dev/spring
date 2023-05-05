package hello.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    public void statefulServiceSingleton() {
        //given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            TestConfig.class);
        StatefulService statefulService1 = ctx.getBean(StatefulService.class);
        StatefulService statefulService2 = ctx.getBean(StatefulService.class);
        //when
        // ThreadA : 김용환 1000원 주문
        int price1 = statefulService1.order("김용환", 1000);
        // ThreadB : 홍길동 2000원 주문
        int price2 = statefulService2.order("홍길동", 2000);
        // ThreadA : 김용환 사용자가 주문 금액 조회
//        int price1 = statefulService1.getPrice();
//        int price2 = statefulService2.getPrice();
        //then
        Assertions.assertThat(price1).isEqualTo(1000);
        Assertions.assertThat(price2).isEqualTo(2000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}

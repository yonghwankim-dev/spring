package hello.core.scope;

import hello.core.scope.SingletonTest.SingletonBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class ProtoTypeTest {

    // 프로토타입 빈은 스프링 컨테이너 종료시 destroy 메소드를 호출되지 않는다. 왜냐하면 스프링 컨테이너는 프로토타입 스코프 빈을 관리하지 않기 때문이다.
    // 따라서 수작업으로 호출해서 destroy해야함
    @Test
    public void prototypeBeanFind() {
        // given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        // when
        ProtoTypeBean bean = ctx.getBean(ProtoTypeBean.class);
        ProtoTypeBean bean2 = ctx.getBean(ProtoTypeBean.class);
        // then
        Assertions.assertThat(bean)
            .isNotSameAs(bean2);

        bean.destroy();
        bean2.destroy();
        ctx.close(); // 직접 프로토타입 스코프 빈을 destroy하지 않음
    }

    @Scope("prototype")
    static class ProtoTypeBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}

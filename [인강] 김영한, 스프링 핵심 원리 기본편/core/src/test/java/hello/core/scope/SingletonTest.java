package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

    @Test
    public void singletonBeanFind() {
        // given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SingletonBean.class);
        // when
        SingletonBean bean = ctx.getBean(SingletonBean.class);
        SingletonBean bean2 = ctx.getBean(SingletonBean.class);
        // then
        Assertions.assertThat(bean)
            .isSameAs(bean2);
        ctx.close();
    }

    @Scope("singleton")
    static class SingletonBean {

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

package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {


    @Test
    public void prototypeFind() {
        // given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PrototypeBean.class);
        // when
        PrototypeBean bean1 = ctx.getBean(PrototypeBean.class);
        PrototypeBean bean2 = ctx.getBean(PrototypeBean.class);
        bean1.addCount();
        bean2.addCount();
        // then
        Assertions.assertThat(bean1.getCount())
            .isEqualTo(1);
        Assertions.assertThat(bean2.getCount())
            .isEqualTo(1);

    }

    // 싱글톤 빈이 가지고 있는 프로토타입 빈은 사용할때마다 새로 생성되는 것이 아닌 싱글톤 빈 생성 및 의존관계 주입시점에 한번만 프로토타입 빈을 생성하여 주입하는 것이다.
    @Test
    public void singletonClientUsePrototype() {
        // given
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        // when
        ClientBean clientBean1 = ctx.getBean(ClientBean.class);
        ClientBean clientBean2 = ctx.getBean(ClientBean.class);
        clientBean1.logic();
        int count = clientBean2.logic();
        // then
        Assertions.assertThat(count)
            .isEqualTo(2);

    }

    @Test
    public void singletonClientUsePrototypeSolution1() {
        // given
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);
        // when
        ClientBean2 bean1 = ctx.getBean(ClientBean2.class);
        ClientBean2 bean2 = ctx.getBean(ClientBean2.class);
        // then
        Assertions.assertThat(bean1.logic())
            .isEqualTo(1);
        Assertions.assertThat(bean2.logic())
            .isEqualTo(1);
    }

    @Test
    public void singletonClientUsePrototypeSolution2() {
        // given
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(ClientBean3.class, PrototypeBean.class);
        // when
        ClientBean3 bean1 = ctx.getBean(ClientBean3.class);
        ClientBean3 bean2 = ctx.getBean(ClientBean3.class);
        // then
        Assertions.assertThat(bean1.logic())
            .isEqualTo(1);
        Assertions.assertThat(bean2.logic())
            .isEqualTo(1);
    }

    @Test
    public void singletonClientUsePrototypeSolution3() {
        // given
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(ClientBean4.class, PrototypeBean.class);
        // when
        ClientBean4 bean1 = ctx.getBean(ClientBean4.class);
        ClientBean4 bean2 = ctx.getBean(ClientBean4.class);
        // then
        Assertions.assertThat(bean1.logic())
            .isEqualTo(1);
        Assertions.assertThat(bean2.logic())
            .isEqualTo(1);
    }


    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    static class ClientBean {

        private final PrototypeBean prototypeBean;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    static class ClientBean2 {

        @Autowired
        private ApplicationContext ctx;

        public int logic() {
            PrototypeBean prototypeBean = ctx.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    static class ClientBean3 {

        @Autowired
        private ObjectProvider<PrototypeBean> provider;

        public int logic() {
            PrototypeBean prototypeBean = provider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    static class ClientBean4 {

        @Autowired
        private Provider<PrototypeBean> provider;

        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }
}

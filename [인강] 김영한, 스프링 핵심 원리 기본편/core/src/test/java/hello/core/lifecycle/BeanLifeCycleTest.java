package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        //given
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
            LifeCycleConfig.class);
        //when
        NetworkClient networkClient = ctx.getBean(NetworkClient.class);
        ctx.close();
        //then
    }

    @Test
    public void lifeCycleTest2() {
        //given
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
            LifeCycleConfig.class);
        //when
        NetworkClient2 networkClient = ctx.getBean(NetworkClient2.class);
        ctx.close();
        //then
    }

    @Test
    public void lifeCycleTest3() {
        //given
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
            LifeCycleConfig.class);
        //when
        NetworkClient3 networkClient = ctx.getBean(NetworkClient3.class);
        ctx.close();
        //then
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient2 networkClient2() {
            NetworkClient2 networkClient = new NetworkClient2();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

        @Bean
        public NetworkClient3 networkClient3() {
            NetworkClient3 networkClient3 = new NetworkClient3();
            networkClient3.setUrl("http://hello-spring.dev");
            return networkClient3;
        }
    }
}

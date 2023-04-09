package com.bean_lifecycle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class NetworkClientTest {

    @Test
    public void 인터페이스를_이용한_스프링_빈_초기화_소멸_테스트() {
        //given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            NetworkClientFactory.class);
        //when
        NetworkClient networkClient = ctx.getBean("networkClient", NetworkClient.class);
        //then
        ctx.close();
    }

    @Test
    public void 설정정보를_이용한_스프링_빈_초기화_소멸_테스트() {
        //given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            NetworkClientFactory.class);
        //when
        NetworkClient2 networkClient2 = ctx.getBean("networkClient2", NetworkClient2.class);
        //then
        ctx.close();
    }

}

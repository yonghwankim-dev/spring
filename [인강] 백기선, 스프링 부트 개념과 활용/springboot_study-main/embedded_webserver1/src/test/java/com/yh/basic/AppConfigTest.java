package com.yh.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;


public class AppConfigTest {


    // @Configuration 사용하는 경우
    @Test
    public void configurationTest() throws Exception{
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //when
        AppConfig appConfig = ac.getBean(AppConfig.class);

        //then
        System.out.println(appConfig);
    }
}
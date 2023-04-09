package com.yh.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



public class AppConfig {

    @Bean
    public void method1(){
        System.out.println("call AppConfig.method1");


    }

    @Bean
    public void method2(){
        System.out.println("call AppConfig.method2");
        method1();
    }



}

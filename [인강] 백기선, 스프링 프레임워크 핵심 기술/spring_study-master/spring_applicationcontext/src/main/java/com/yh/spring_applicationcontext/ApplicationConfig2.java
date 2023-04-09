package com.yh.spring_applicationcontext;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 자바 설정 파일을 이용한 빈 설정 방법 2 : @ComponentScan 어노테이션
@Configuration
@ComponentScan(basePackageClasses = SpringApplicationcontextApplication.class)
public class ApplicationConfig2 {

}

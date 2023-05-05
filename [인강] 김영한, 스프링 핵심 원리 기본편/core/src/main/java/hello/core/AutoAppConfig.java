package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

// 기존 AppConfig 수동 빈 등록 예제 코드를 남기기 위해서 Configuration 애노테이션이 붙은 클래스를 빈등록을 제외하였다.
@Configuration
@ComponentScan(
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
    basePackages = "hello.core.member", // hello.core.member 디렉토리 이하의 컴포넌트만 빈으로 등록함
    basePackageClasses = AutoAppConfig.class, // hello.core 패키지를 기준으로 컴포넌트 스캔 수행
    useDefaultFilters = true // default=true, false로 설정시 @Component, @Service, @Controller, @Repository 애노테이션된 클래스가 빈 등록이 안됨
)
public class AutoAppConfig {

}

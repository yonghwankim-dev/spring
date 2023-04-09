package kr.yh.external_config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SampleRunner implements ApplicationRunner {

    private final YonghwanProperties yonghwanProperties;

    @Override
    public void run(ApplicationArguments args){
        System.out.println("===================");
        System.out.println(yonghwanProperties.getName());
        System.out.println(yonghwanProperties.getAge());
        System.out.println(yonghwanProperties.getFullName());
        System.out.println(yonghwanProperties.getSessionTimeout());
        System.out.println("===================");
    }
}

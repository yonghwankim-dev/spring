package kr.yh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 비동기 활성화, 단순 @Async만 붙였다고 비동기되지 않음
public class ApplicationEventPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEventPublisherApplication.class, args);
    }

}

package kr.yh.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test") // 프로파일 정의 방법1
public class TestConfiguration {
    // 이 빈설정은 테스트 프로파일일때만 활성화되는 빈입니다.
    @Bean
//    @Profile("test") // 프로파일 정의 방법 2
    public TestBookRepository testBookRepository(){
        return new TestBookRepository();
    }
}

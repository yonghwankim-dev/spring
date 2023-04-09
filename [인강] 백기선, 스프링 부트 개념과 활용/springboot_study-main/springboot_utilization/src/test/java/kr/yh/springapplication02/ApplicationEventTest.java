package kr.yh.springapplication02;

import kr.yh.SpringbootUtilizationApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationEventTest {
    @Test
    public void applicationStartingEventTest(){
        SpringApplication application = new SpringApplication(SpringbootUtilizationApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);

        application.addListeners(new ApplicationStartingEventListener());
        application.run();
    }

    @Test
    public void applicationStartedEventTest(){
        SpringApplication application = new SpringApplication(SpringbootUtilizationApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);

        application.addListeners(new ApplicationStartedEventListener());
        application.run();
    }

    @Test
    public void webApplicationType_SERVLET_Test(){
        SpringApplication application = new SpringApplication(SpringbootUtilizationApplication.class);
        application.setWebApplicationType(WebApplicationType.SERVLET); // SERVLET 받는 경우

        application.run();
    }

    // 애플리케이션 매개변수 사용하기
    @Test
    public void applicationArgumentUsingTest(){
        SpringApplication application = new SpringApplication(SpringbootUtilizationApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);

        application.run("-Dfoo", "--bar"); // false, true
    }
}
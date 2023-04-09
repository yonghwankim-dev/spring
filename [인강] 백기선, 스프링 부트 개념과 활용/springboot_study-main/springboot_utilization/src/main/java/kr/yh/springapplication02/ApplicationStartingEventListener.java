package kr.yh.springapplication02;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// ApplicationStartingEvent는 ApplicationContext가 생성되기 전에 발생하는 이벤트로써
// SimpleListener 빈을 등록하고 사용할 수 없다.
// ApplicationStartingEvent를 실행하기 위해서는 명시적으로 추가해주어야 한다
@Component
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("=============================");
        System.out.println("Application is Starting");
        System.out.println("=============================");
    }
}

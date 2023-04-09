package kr.yh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    // ApplicationContext 클래스는 ApplicationEventPublisher를 상속받았기 때문에 publishEvent를 사용할 수 있음
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 이벤트를 발생시킴
//        applicationContext.publishEvent(new MyEvent(this, 100));
        applicationContext.publishEvent(new MyEvent2(this, 100));
    }
}

package kr.yh;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AnotherHandler {
    @EventListener
//    @Order(Ordered.HIGHEST_PRECEDENCE + 2) // 가장 높은 우선순위/
    @Async
    public void handle(MyEvent2 event){
        System.out.println(Thread.currentThread().toString());
        System.out.println("AnotherHandler : 이벤트를 받았다. 데이터는 " + event.getData() + " 입니다.");
    }
}

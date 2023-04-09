package kr.yh;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Component
public class MyEventHandler2{

    @EventListener
//    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Async // 비동기적으로 수행됨
    public void handle(MyEvent2 event) {
        System.out.println(Thread.currentThread());
        System.out.println("MyEventHandler2 : 이벤트를 받았다. 데이터는 " + event.getData() + " 입니다.");
    }

    @EventListener
    @Async
    public void handle(ContextRefreshedEvent event){
        System.out.println(Thread.currentThread());
        System.out.println("MyEventHandler2 ContextRefreshedEvent");
    }

    @EventListener
    @Async
    public void handle(ContextStartedEvent event){
        System.out.println(Thread.currentThread());
        System.out.println("MyEventHandler2 ContextStartedEvent");
    }

    @EventListener
    @Async
    public void handle(ContextClosedEvent event){
        System.out.println(Thread.currentThread());
        System.out.println("MyEventHandler2 ContextClosedEvent");
    }

    @EventListener
    @Async
    public void handle(RequestHandledEvent event){
        System.out.println(Thread.currentThread());
        System.out.println("MyEventHandler2 RequestHandledEvent");
    }
}

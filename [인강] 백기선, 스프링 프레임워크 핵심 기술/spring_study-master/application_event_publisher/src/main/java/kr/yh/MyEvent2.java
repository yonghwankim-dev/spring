package kr.yh;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

// 스프링 4.2부터는 ApplicationEvent를 상속받지 않고도 이벤트를 생성할 수 있음
@Getter
public class MyEvent2{
    private Object source;
    private int data;

    public MyEvent2(Object source, int data) {
        this.source = source;
        this.data = data;
    }
}

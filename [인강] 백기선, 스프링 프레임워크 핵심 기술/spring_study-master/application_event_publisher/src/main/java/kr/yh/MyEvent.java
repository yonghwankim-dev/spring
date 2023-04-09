package kr.yh;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

// ApplicationEvent를 상속받아 이벤트 생성
@Getter
public class MyEvent extends ApplicationEvent {
    private int data;

    public MyEvent(Object source) {
        super(source);
    }

    public MyEvent(Object source, int data){
        super(source);
        this.data = data;
    }
}

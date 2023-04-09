package kr.yh;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Event {
    private Long id;
    private String title;

    public Event(Long id){
        this.id = id;
    }
}

package kr.yh;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

// 쓰레드 안전하지 않기 때문에 빈으로 등록하면 안됨
public class EventEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Event event = (Event) getValue();
        return event.getId().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Event(Long.parseLong(text)));
    }
}

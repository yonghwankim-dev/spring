package kr.yh;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Primary
@Component
@AllArgsConstructor
public class ProxySimpleEventService implements EventService{
    @Qualifier("simpleEventService")
    private final EventService eventService;

    @Override
    public void createEvent() {
        long begin = System.currentTimeMillis();
        eventService.createEvent();
        long end = System.currentTimeMillis() - begin;
        System.out.println(Math.round(end));
    }

    @Override
    public void publishEvent() {
        long begin = System.currentTimeMillis();
        eventService.publishEvent();
        long end = System.currentTimeMillis() - begin;
        System.out.println(Math.round(end));
    }

    @Override
    public void deleteEvent() {
        eventService.deleteEvent();
    }
}
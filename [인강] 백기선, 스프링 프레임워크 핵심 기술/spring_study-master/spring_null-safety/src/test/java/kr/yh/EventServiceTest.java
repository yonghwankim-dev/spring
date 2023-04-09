package kr.yh;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventServiceTest {
    @Autowired
    EventService eventService;


    @Test
    public void testCreateEvent_whenParameterIsNull(){
        //given

        //when
        String result = eventService.createEvent(null);
        //then
    }
    
    @Test
    public void testPublishEvent_whenReturnIsNull(){
        //given
        
        //when
        String result = eventService.publishEvent();
        //then
    }
}
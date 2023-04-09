package kr.yh;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SimpleEventServiceTest {
    @Autowired
    private EventService eventService;

    @Qualifier("simpleEventService")
    @Autowired
    private EventService eventService2;

    @Test
    public void testCreatedEvent(){
        eventService.createEvent();
    }

    @Test
    public void testPublishedEvent(){
        eventService.publishEvent();
    }

    @Test
    public void testPerfAspect(){
        eventService2.createEvent();
        eventService2.publishEvent();
    }
}

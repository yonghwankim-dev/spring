package kr.yh;

import org.springframework.stereotype.Service;

// Real Subject
@Service
public class SimpleEventService implements EventService {
    @PerfLogging
    @Override
    public void createEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Created an Event");
    }

    @PerfLogging
    @Override
    public void publishEvent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Published an event");
    }

    @Override
    public void deleteEvent() {
        System.out.print("Deleted an Event");
    }
}

package com.scheduled_testing;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Hello {

    private int value;

    @Scheduled(cron = "0 0 0 * * *")
    public void hello() {
        value++;
        System.out.println("hello world : " + LocalDateTime.now());
    }

    public int getValue() {
        return value;
    }
}

package com.scheduled_testing;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Counter {
    private AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 5)
    public void scheduled() {
        this.count.incrementAndGet();
    }

    public int getInvocationCount() {
        return this.count.get();
    }
}

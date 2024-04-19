package com.scheduled_testing;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ScheduledConfig.class)
public class ScheduledIntegrationTest {

    @Autowired
    Counter counter;

    @Test
    public void givenSleepBy100ms_whenGetInvocationCount_thenIsGreaterThanZero()
            throws InterruptedException {
        Thread.sleep(100L);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(counter.getInvocationCount()).isGreaterThan(0);
            softAssertions.assertAll();
        });
    }
}

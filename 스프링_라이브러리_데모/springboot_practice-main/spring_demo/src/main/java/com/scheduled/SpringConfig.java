package com.scheduled;

import java.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class SpringConfig {
    @Scheduled(fixedDelay = 5000)
    public void scheduleFixedDelayTask() throws InterruptedException {
        System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
        Thread.sleep(1000);
    }

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTask() throws InterruptedException {
        System.out.println("Fixed rate task -" + System.currentTimeMillis() / 1000);
        Thread.sleep(1000);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 3000)
    public void scheduleFixedRateWithInitialDelayTask() {
        System.out.println("Fixed rate task with one second initial delay - " + System.currentTimeMillis() / 1000);
    }

    // 3초마다 실행 합니다.
    @Scheduled(cron = "0/3 * * * * *")
    public void scheduleTaskUsingCronExpression() {
        System.out.println("schedule tasks using cron jobs - " + LocalDateTime.now());
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void scheduleFixedDelayTaskWithParameterizing() {
        System.out.println("Fixed delay task with parameter - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void scheduleFixedRateTaskWithParameterizing() {
        System.out.println("Fixed rate task with parameter - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(cron = "${cron.expression}")
    public void scheduleCronTaskWithParameterizing() {
        System.out.println("schedule tasks using cron jobs - " + LocalDateTime.now());
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }
}

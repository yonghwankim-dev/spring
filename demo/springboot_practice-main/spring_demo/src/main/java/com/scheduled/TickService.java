package com.scheduled;

import org.springframework.stereotype.Service;

@Service
public class TickService {

    public void tick() {
        System.out.println("call tick");
    }

    // 밀리세컨드 단위
    public long getDelay() {
        return 3000;
    }
}

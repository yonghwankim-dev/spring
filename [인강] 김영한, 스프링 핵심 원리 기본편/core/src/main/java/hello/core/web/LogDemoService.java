package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogDemoService {

    private final MyLogger logger;

    public void logic(String id) {
        logger.log("service id = " + id);
    }
}

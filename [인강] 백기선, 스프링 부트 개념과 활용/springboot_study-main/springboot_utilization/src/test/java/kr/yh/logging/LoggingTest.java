package kr.yh.logging;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoggingTest {
    private Logger logger = LoggerFactory.getLogger(LoggingTest.class);

    @Test
    public void testInfo(){
        logger.info("hello log");
    }
}

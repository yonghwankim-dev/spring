package kr.yh.springapplication02;

import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ApplicationArgumentListener {
    public ApplicationArgumentListener(ApplicationArguments arguments) {
        System.out.println("foo : " + arguments.containsOption("foo"));
        System.out.println("bar : " + arguments.containsOption("bar"));
    }
}

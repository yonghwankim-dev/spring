package kr.yh.exceptionHandler.controller_level;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class Foo2Controller {
    @ExceptionHandler({CustomException1.class, CustomException2.class})
    public void handleException(){

    }

}

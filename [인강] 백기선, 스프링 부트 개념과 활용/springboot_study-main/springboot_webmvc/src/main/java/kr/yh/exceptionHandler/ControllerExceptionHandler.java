package kr.yh.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SampleException.class)
    public @ResponseBody AppError sampleError(SampleException e){
        return new AppError("error.app.key", "IDK IDK");
    }
}

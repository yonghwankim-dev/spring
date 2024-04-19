package com;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

// 모든 컨트롤러를 대상으로 글로벌 예외처리를 수행합니다.
@RestControllerAdvice
public class ControllerSupport {
    // 다음과 같은 예외 발생시 handle 메서드를 호출합니다.
    @ExceptionHandler({NullPointerException.class, IOException.class, IllegalStateException.class})
    // @ResponseStatus 애노테이션을 사용하여 Http Status Code에도 어떤 에러인지 명시적으로 내어줄 수 있음
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handle(Exception ex){
        return "exception handle!";
    }
}

package kr.yh.exceptionHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppError {
    private String message;
    private String reason;

    public AppError(String message, String reason) {
        this.message = message;
        this.reason = reason;
    }
}

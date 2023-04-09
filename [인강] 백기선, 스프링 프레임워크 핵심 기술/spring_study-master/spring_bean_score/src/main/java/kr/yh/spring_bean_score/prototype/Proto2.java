package kr.yh.spring_bean_score.prototype;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
@Getter
public class Proto2 {
    @Autowired
    private Single2 single;
}

package kr.yh.spring_bean_score.prototype;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Single2 {
    @Autowired
    private Proto3 proto;
}

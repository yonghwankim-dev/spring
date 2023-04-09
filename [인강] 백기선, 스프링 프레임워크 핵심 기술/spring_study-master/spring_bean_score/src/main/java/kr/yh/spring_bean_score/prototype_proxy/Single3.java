package kr.yh.spring_bean_score.prototype_proxy;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Single3 {
    @Autowired
    private ProtoProxy proto;
}

package kr.yh.spring_bean_score.singletom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SingletonTest {
    @Autowired
    ApplicationContext ctx;

    // Single, Proto 빈은 스코프를 지정하지 않았기 때문에 기본 설정인 싱글톤 빈이 된다.
    @Test
    public void test() throws Exception{
        //given
        Single single1 = ctx.getBean(Single.class);
        Single single2 = ctx.getBean(Single.class);
        Proto proto1 = ctx.getBean(Proto.class);
        Proto proto2 = ctx.getBean(Proto.class);
        //when
        boolean actual1 = single1.equals(single2);
        boolean actual2 = proto1.equals(proto2);
        //then
        System.out.println(single1);
        System.out.println(single2);
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }
}

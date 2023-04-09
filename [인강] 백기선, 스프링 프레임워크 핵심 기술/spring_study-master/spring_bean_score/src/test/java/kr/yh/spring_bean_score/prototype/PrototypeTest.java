package kr.yh.spring_bean_score.prototype;

import kr.yh.spring_bean_score.singletom.Single;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PrototypeTest {

    @Autowired
    ApplicationContext ctx;

    // Proto 빈에 Scope로 "prototype"으로 설정한 결과
    // 빈을 참조할때마다 인스턴스를 새로 생성하여 줌
    @Test
    public void test() throws Exception{
        //given
        Proto2 proto1 = ctx.getBean(Proto2.class);
        Proto2 proto2 = ctx.getBean(Proto2.class);
        //when
        boolean actual = proto1.equals(proto2);
        //then
        System.out.println(proto1);
        System.out.println(proto2);
        assertThat(actual).isFalse();
    }

    // 프로토타입 빈이 싱글톤을 참조하는 테스트
    @Test
    public void test2() throws Exception{
        //given
        Proto2 proto1   = ctx.getBean(Proto2.class);
        Single2 single1 = ctx.getBean(Single2.class);
        //when
        boolean actual = proto1.getSingle().equals(single1);
        //then
        System.out.println(proto1.getSingle());
        System.out.println(single1);
        assertThat(actual).isTrue();
    }

    // 싱글톤 타입 빈이 프로토타입 빈을 참조하는 테스트
    // 싱글톤 타입 빈의 프로토타입 의존 객체 빈은 업데이트가 되지 않음
    @Test
    public void test3() throws Exception{
        //given
        Single2 single1 = ctx.getBean(Single2.class);
        Single2 single2 = ctx.getBean(Single2.class);
        Proto3 proto1 = single1.getProto();
        Proto3 proto2 = single2.getProto();
        //when
        boolean actual = proto1.equals(proto2);
        //then
        System.out.println(proto1);
        System.out.println(proto2);
        assertThat(actual).isTrue();
    }



}

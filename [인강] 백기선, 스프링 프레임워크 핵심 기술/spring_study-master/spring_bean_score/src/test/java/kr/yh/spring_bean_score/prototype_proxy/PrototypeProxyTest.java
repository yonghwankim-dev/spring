package kr.yh.spring_bean_score.prototype_proxy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PrototypeProxyTest {
    @Autowired
    ApplicationContext ctx;

    // 싱글톤 타입 빈이 프로토타입 빈을 업데이트 하게 하려면? => 프로포타입 빈의 프록시 모드 사용
    // 테스트 결과 : protoProxy1, protoProxy2는 메모리 주소는 다르나 사실 다 같은 객체
    // 싱글톤에서 getProto()로 꺼낸 객체는 사실 다 같은 객체 (즉, ProtoProxy 싱글톤 객체를 반환함)
    // 그런데 ProtoProxy 객체를 통해서 하는 모든 오퍼레이션(toStirng, get...)
    // 등을 호출하는 순간 프록시가 적용되어 새로운 객체를 만든 다음에
    // 그 객체의 오퍼레이션을 호출하기 때문에 매번 다른 해시코드나 UUID가 출력됨
    // equals의 경우에는 프록시가 적용되지 않고 Object.equals가 수행되기 때문에 protoProxy1과 protoProxy2가 같은 걸로 나옴
    @Test
    public void test4() throws Exception{
        //given
        Single3 single1 = ctx.getBean(Single3.class);
        Single3 single2 = ctx.getBean(Single3.class);
        ProtoProxy protoProxy1 = single1.getProto();
        ProtoProxy protoProxy2 = single2.getProto();

        //when
        boolean actual = protoProxy1.equals(protoProxy2);
        //then
        // protoProxy1은 toString을 호출했기 때문에 서로 다른 메모리 주소가 나옴
        System.out.println(protoProxy1);
        System.out.println(protoProxy1);
        assertThat(actual).isTrue();
    }

}

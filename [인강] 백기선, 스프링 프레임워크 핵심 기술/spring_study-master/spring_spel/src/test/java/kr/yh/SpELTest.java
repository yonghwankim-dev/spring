package kr.yh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles({"test"})
public class SpELTest {
    // #{"표현식"}, ${"프로퍼티"}
    @Value("#{1 + 1}")
    int value1;

    @Value("#{'hello ' + 'world'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean oneEqualOne;

    @Value("hello")
    String hello;

    @Value("${my.value}")
    int myValue;

    @Value("#{'spring'}")
    String spring;

    @Value("#{${my.value} + '번'}")
    String valueBun;

    @Value("#{person.age}")
    int age;

    @Test
    public void testValue1(){
        assertThat(value1).isEqualTo(2);
    }

    @Test
    public void testGreeting(){
        assertThat(greeting).isEqualTo("hello world");
    }

    @Test
    public void testOneEqualOne(){
        assertThat(oneEqualOne).isTrue();
    }

    @Test
    public void testHello(){
        assertThat(hello).isEqualTo("hello");
    }

    @Test
    public void testMyValue(){
        assertThat(myValue).isEqualTo(10);
    }

    @Test
    public void testValueBun(){
        assertThat(valueBun).isEqualTo("10번");
    }

    @Test
    public void testSpring(){
        assertThat(spring).isEqualTo("spring");
    }

    @Test
    public void testAge(){
        assertThat(age).isEqualTo(20);
    }
    
    @Test
    public void testExpressionParser(){
        //given
        ExpressionParser parser = new SpelExpressionParser();
        //when
        Expression expression = parser.parseExpression("2 + 100");
        Integer actual = expression.getValue(Integer.class);
        //then
        assertThat(actual).isEqualTo(102);
    }
}

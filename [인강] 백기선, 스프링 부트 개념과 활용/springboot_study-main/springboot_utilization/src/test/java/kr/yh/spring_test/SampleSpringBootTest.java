package kr.yh.spring_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(properties = {"name=yonghwan",
                              "spring.config.location = classpath:test.yml"})
public class SampleSpringBootTest {
    @Value("${name}")
    private String name;

    @Value("${yonghwan.age}")
    private int age;

    @Test
    public void testName(){
        assertThat(name).isEqualTo("yonghwan");
    }

    @Test
    public void testAge(){
        assertThat(age).isEqualTo(20);
    }
}

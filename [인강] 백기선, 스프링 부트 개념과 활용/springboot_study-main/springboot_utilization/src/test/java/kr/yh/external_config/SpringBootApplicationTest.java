package kr.yh.external_config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:test.yml"})
public class SpringBootApplicationTest {

    @Value("${yonghwan.name}")
    private String name;

    @Value("${yonghwan.age}")
    private long age;

    @Value("${my.secret}")
    private String mySecret;

    @Value("${my.bignumber}")
    private long myBigNumber;

    @Value("${my.uuid}")
    private String myUuid;

    @Value("${my.number.less.than.ten}")
    private long myNumberLessThanTen;

    @Value("${my.number.in.range}")
    private long myNumberInRange;

    @Test
    public void test(){
        //given

        //when
        String actual = name;
        //then
        assertThat(actual).isEqualTo("YongHwan");
    }

    @Test
    public void test2(){
        //given

        //when
        long actual = age;
        //then
        assertThat(actual).isEqualTo(20);
    }

    @Test
    public void randomValueTest(){
        //given

        //when
        String actual1 = mySecret;
        long actual2 = myBigNumber;
        String actual3 = myUuid;
        long actual4 = myNumberLessThanTen;
        long actual5 = myNumberInRange;
        //then
        System.out.println(actual1);
        System.out.println(actual2);
        System.out.println(actual3);
        System.out.println(actual4);
        System.out.println(actual5);
    }
}

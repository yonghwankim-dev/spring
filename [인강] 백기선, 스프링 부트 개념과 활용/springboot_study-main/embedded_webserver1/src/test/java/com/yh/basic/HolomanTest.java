package com.yh.basic;

import com.yh.Holoman;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HolomanTest {

    @Autowired
    Holoman holoman;

    @Test
    public void beanTest() throws Exception{
        //given

        //when

        //then
        assertThat(holoman.getHowLong()).isEqualTo(60);
    }
}

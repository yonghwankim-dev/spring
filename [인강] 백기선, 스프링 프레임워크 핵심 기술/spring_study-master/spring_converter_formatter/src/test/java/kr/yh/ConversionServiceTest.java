package kr.yh;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ConversionServiceTest {

    @Autowired
    ConversionService conversionService;
    
    @Test
    public void webConversionServiceClassTest(){
        //given
        
        //when
        boolean actual = conversionService instanceof WebConversionService;
        //then
        assertThat(actual).isTrue();
    }
}

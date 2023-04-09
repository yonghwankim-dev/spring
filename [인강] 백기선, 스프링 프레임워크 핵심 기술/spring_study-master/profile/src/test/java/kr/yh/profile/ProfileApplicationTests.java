package kr.yh.profile;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@PropertySource(value="classpath:/app.properties")
@ActiveProfiles("test")
class ProfileApplicationTests {

    @Autowired
    ApplicationContext ctx;

//    테스트 프로파일을 설정하지 않았기 때문에 주입할 수 없음
//    @Autowired
//    TestBookRepository testBookRepository;

    // ApplicationContext에 기본적으로 설정된 프로파일들을 출력함
    @Test
    public void environment() throws Exception{
        //given
        Environment environment = ctx.getEnvironment();
        //when
        String[] profiles = environment.getDefaultProfiles();
        //then
        assertThat(Arrays.toString(profiles)).isEqualTo("[default]");
    }
    
    @Test
    public void testProfile_testBookRepository() throws Exception{
        //given

        //when
        
        //then

    }

    @Test
    public void property() throws Exception{
        //given
        Environment environment = ctx.getEnvironment();
        //when
        String actual = environment.getProperty("app.name");
        //then
        assertThat(actual).isEqualTo("spring");
    }

}

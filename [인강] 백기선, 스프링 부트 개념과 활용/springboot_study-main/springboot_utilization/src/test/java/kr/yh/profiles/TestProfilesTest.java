package kr.yh.profiles;

import kr.yh.external_config.YonghwanProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = {"test"})
public class TestProfilesTest {
    @Autowired
    private String hello;

    @Autowired
    private YonghwanProperties yonghwanProperties;

    @Test
    public void testHello(){
        assertThat(hello).isEqualTo("hello test");
    }
    
    @Test
    public void testProfileProperty(){
        assertThat(yonghwanProperties.getName()).isEqualTo("yonghwan test");
    }
}

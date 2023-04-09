package kr.yh.profiles;

import kr.yh.external_config.YonghwanProperties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = {"prod"})
public class ProdProfilesTest {

    @Autowired
    private String hello;
    
    @Autowired
    private YonghwanProperties yonghwanProperties;

    @Test
    public void testHello(){
        assertThat(hello).isEqualTo("hello prod");
    }
    
    @Test
    public void testGetDbName(){
        assertThat(yonghwanProperties.getDbName()).isEqualTo("h2");
    }
}

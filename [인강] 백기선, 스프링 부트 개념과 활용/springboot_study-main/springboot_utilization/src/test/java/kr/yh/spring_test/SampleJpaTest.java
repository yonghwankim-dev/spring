package kr.yh.spring_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SampleJpaTest {
    @Autowired
    private SampleRepository sampleRepository;
    
    @Test
    public void testSave(){
        Sample sample = new Sample(null, "yonghwan", 30);
        Sample savedSample = sampleRepository.save(sample);
        Assertions.assertThat(savedSample).isEqualTo(sample);
    }
}

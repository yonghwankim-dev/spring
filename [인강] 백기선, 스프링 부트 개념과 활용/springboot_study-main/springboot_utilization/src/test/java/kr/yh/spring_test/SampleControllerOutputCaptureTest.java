package kr.yh.spring_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SampleController.class)
@ExtendWith(OutputCaptureExtension.class)
public class SampleControllerOutputCaptureTest {
    @MockBean
    SampleService mockSampleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testHello(CapturedOutput output) throws Exception {
        when(mockSampleService.getName()).thenReturn("yonghwan");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello yonghwan"))
                .andDo(print());

        assertThat(output.toString())
                .contains("kim yonghwan");
    }
}

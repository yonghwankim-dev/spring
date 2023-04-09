package kr.yh.spring_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SampleController.class) // 컨트롤러만 빈으로 등록
public class SampleControllerWebMvcTest {
    @MockBean
    SampleService mockSampleService; // 필요한 빈은 MockBean으로 채워줌

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testHello() throws Exception {
        when(mockSampleService.getName()).thenReturn("yonghwan");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello yonghwan"))
                .andDo(print());

    }
}
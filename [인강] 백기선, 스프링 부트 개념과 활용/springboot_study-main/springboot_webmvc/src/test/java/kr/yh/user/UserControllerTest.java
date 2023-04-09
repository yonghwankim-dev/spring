package kr.yh.user;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.RequestResultMatchers;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    

    @Test
    public void testHello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }
    
    @Test
    public void testCreateUser_JSON() throws Exception {
        String userjson = "{\"username\" : \"yonghwan\", \"password\" : \"123\"}";
        mockMvc.perform(post("/users/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(userjson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username",
                        is(equalTo("yonghwan"))))
                .andExpect(jsonPath("$.password",
                        is(equalTo("123"))));
    }

    @Test
    public void testCreateUser_XML() throws Exception {
        String userjson = "{\"username\" : \"yonghwan\", \"password\" : \"123\"}";
        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_XML)
                        .content(userjson))
                .andExpect(status().isOk())
                .andExpect(xpath("/User/username").string("yonghwan"))
                .andExpect(xpath("/User/password").string("123"));

    }
}

package kr.yh.htmlunit;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import kr.yh.thymeleaf.SampleController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@WebMvcTest(SampleController.class)
public class HtmlUnitTest {
    @Autowired
    WebClient webClient;
    
    @Test
    public void hello() throws IOException {
        HtmlPage page = webClient.getPage("/hello");
        HtmlHeading1 h1 = page.getFirstByXPath("//h1");
        assertThat(h1.getTextContent()).isEqualTo("yonghwan");
    }
}

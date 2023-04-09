package kr.yh;

import kr.yh.external_config.YonghwanProperties;
import kr.yh.springapplication02.ApplicationStartingEventListener;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.*;
import java.util.Arrays;

@SpringBootApplication
public class SpringbootUtilizationApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootUtilizationApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}

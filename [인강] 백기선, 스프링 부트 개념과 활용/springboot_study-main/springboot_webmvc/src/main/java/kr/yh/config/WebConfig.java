package kr.yh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/m/**") // m으로 시작하는 요청이 오면
                .addResourceLocations("classpath:/m/") // 주의사항 : 반드시 '/'으로 끝나야함
                .setCachePeriod(20);

    }
}

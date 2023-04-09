package kr.yh;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final MessageSource messageSource;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new EventConverter.StringToEventConverter());
        registry.addConverter(new EventConverter.EventToStringConverter());
        registry.addFormatter(new EventFormatter(messageSource));
    }
}

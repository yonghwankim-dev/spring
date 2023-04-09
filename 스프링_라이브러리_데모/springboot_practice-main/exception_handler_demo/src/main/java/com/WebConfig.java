package com;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final MessageSource messageSource;

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        DateTimeFormatterRegistrar register = new DateTimeFormatterRegistrar();
//        register.setUseIsoFormat(true);
//        register.registerFormatters(registry);
        registry.addFormatter(new LocalDateFormatter(messageSource));
    }
}

package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @EnableWebMvc 애노테이션은 스프링 MVC 설정을 활성화한다. 스프링 MVC를 사용하는데
// 필요한 다양한 설정을 생성한다.
@Configuration
@EnableWebMvc	// OptionalValidatorFactoryBean을 글로벌 범위 Validator로 등록
public class MvcConfig implements WebMvcConfigurer{

	// DispatcherServlet의 매핑 경로를 "/"로 주었을 때, JSP/HTML/CSS 등을 올바르게
	// 처리하기 위한 설정을 추가한다.
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// JSP를 이용해서 컨트롤러의 실행 결과를 보여주기 위한 설정 추가
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/",".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasename("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

	// 글로벌 범위 Validator를 설정하면
	// OptionalValidatorFactoryBean을 사용하지 않는다.
//	@Override
//	public Validator getValidator() {
//		return new RegisterRequestValidator();
//	}
	
	
}

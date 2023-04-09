package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @EnableWebMvc 애노테이션은 스프링 MVC 설정을 활성화한다. 스프링 MVC를 사용하는데
// 필요한 다양한 설정을 생성한다.
@Configuration
@EnableWebMvc
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
	
	
	
}

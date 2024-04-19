package nemo.filter.registration.step01;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>(
			new CorsFilter());
		registrationBean.setUrlPatterns(List.of("/*"));
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<CustomRequestFilter> customRequestFilter(){
		FilterRegistrationBean<CustomRequestFilter> registrationBean = new FilterRegistrationBean<>(new CustomRequestFilter());
		registrationBean.setUrlPatterns(List.of("/*"));
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<CustomResponseFilter> customResponseFilter(){
		FilterRegistrationBean<CustomResponseFilter> registrationBean = new FilterRegistrationBean<>(new CustomResponseFilter());
		registrationBean.setUrlPatterns(List.of("/*"));
		return registrationBean;
	}
}

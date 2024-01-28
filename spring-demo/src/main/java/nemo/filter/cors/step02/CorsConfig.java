package nemo.filter.cors.step02;

import static org.springframework.http.HttpMethod.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*")
			.allowedOrigins("*")
			.allowedMethods(GET.name(), POST.name(), PUT.name(), DELETE.name(), PATCH.name(), OPTIONS.name())
			.allowedHeaders("*")
			.allowCredentials(false).maxAge(6000);
	}
}

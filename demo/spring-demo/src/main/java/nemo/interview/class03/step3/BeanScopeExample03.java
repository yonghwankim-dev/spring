package nemo.interview.class03.step3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class BeanScopeExample03 {

	@Getter
	@Setter
	@AllArgsConstructor
	static class HelloMessageGenerator{
		private String message;
	}

	@Configuration
	static class AppConfig{

		@Bean
		@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
		public HelloMessageGenerator requestScopedBean(){
			return new HelloMessageGenerator("hello");
		}
	}

	@RestController
	static class ScopesController{
		@Resource(name = "requestScopedBean")
		private HelloMessageGenerator requestScopedBean;

		@RequestMapping("/scopes/request")
		public String getRequestScopeMessage() {
			requestScopedBean.setMessage("Good morning!");
			return requestScopedBean.getMessage();
		}
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanScopeExample03.class);
		// ScopesController controller = ctx.getBean(ScopesController.class);
		HelloMessageGenerator helloMessageGenerator = ctx.getBean(HelloMessageGenerator.class);

		System.out.println(helloMessageGenerator);
		// System.out.println(controller.getRequestScopeMessage());
		// System.out.println(controller.getRequestScopeMessage());

		// ctx.close();
	}

}

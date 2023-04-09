package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @Controller 애노테이션을 적용한 클래스는 스프링 MVC에서 컨트롤러로 사용한다.
@Controller
public class HelloController {
	// @GetMapping 애노테이션은 메서드가 처리할 요청 경로를 지정한다.
	// 위 코드의 경우 "/hello" 경로로 들어온 요청을 hello() 메서드를 이용해서 처리한다고 설정했다.
	// 이름에서 알 수 있듯이 HTTP 요청 메서드 중 GET 메서드에 대한 매핑을 설정한다.
	@GetMapping("/hello")
	public String hello(Model model,	// Model 파라미터는 컨트롤러의 처리 결과를 뷰에 전달할때 사용된다.
			// @RequestParam 애노테이션은 HTTP 요청 파라미터의 값을 메서드의 파라미터로 전달할때 사용된다.
			// 아래의 경우에는 name 요청 파라미터의 값을 name 파라미터에 전달한다.
			@RequestParam(value="name", required = false) String name) {
		// "greeting"이라는 모델 속성에 값을 설정한다.
		model.addAttribute("greeting", "안녕하세요, " + name);
		// 컨트롤러의 처리 결과를 보여줄 뷰 이름으로 "hello"를 사용한다.
		return "hello";
	}
}

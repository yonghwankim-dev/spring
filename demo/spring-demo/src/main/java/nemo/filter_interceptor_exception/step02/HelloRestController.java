package nemo.filter_interceptor_exception.step02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
}

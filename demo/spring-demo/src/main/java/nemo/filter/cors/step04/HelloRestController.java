package nemo.filter.cors.step04;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
	origins = {"http://localhost:8080"},
	allowedHeaders = "*",
	methods = {RequestMethod.GET},
	maxAge = 3600L)
@RestController
public class HelloRestController {

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
}

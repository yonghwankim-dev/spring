package io.security.basicsecurity.step03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	@GetMapping("/")
	public String index() {
		return "home";
	}

	@PostMapping("/")
	public String postHome(){
		return "home";
	}
}

package io.security.basicsecurity.step02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
}

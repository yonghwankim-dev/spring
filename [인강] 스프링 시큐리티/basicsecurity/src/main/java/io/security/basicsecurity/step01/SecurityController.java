package io.security.basicsecurity.step01;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	@GetMapping("/")
	public String index() {
		return "home";
	}

	@GetMapping("/loginPage")
	public String loginPage() {
		return "loginPage";
	}
}

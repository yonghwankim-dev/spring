package io.security.basicsecurity.step02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	@GetMapping("/")
	public String index() {
		return "home";
	}

	@GetMapping("/user")
	public String user(){
		return "user";
	}

	@GetMapping("/admin/pay")
	public String adminPay(){
		return "adminPay";
	}

	@GetMapping("/admin/**")
	public String admin(){
		return "admin";
	}

	@GetMapping("/denied")
	public String denied(){
		return "Access is denied";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}
}

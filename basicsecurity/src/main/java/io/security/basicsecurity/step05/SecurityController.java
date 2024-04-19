package io.security.basicsecurity.step05;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SecurityController {
	@GetMapping("/")
	public String index(HttpSession session) {
		// Authentication 객체 참조 방법1 : SeucirtyContextHolder
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Authentication 객체 참조 방법2 : HttpSession, HttpSessionSecurityContextRepository
		SecurityContext securityContext = (SecurityContext)session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		Authentication authentication2 = securityContext.getAuthentication();

		// authentication과 authentication2는 동일한 객체입니다.
		System.out.println("authentication = " + authentication);
		System.out.println("authentication2 = " + authentication2);
		return "home";
	}

	@GetMapping("/thread")
	public String thread() throws InterruptedException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("authentication : {}", authentication);
		Thread thread = new Thread(() -> {
			Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
			log.info("authentication : {}", authentication2);
		});
		thread.start();
		thread.join();
		return "thread";
	}
}

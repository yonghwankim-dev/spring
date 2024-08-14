package nemo.listener.event_listener;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

	private final MemberService memberService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestParam String name) {
		memberService.signup(name);
		return ResponseEntity.ok("ok");
	}
}

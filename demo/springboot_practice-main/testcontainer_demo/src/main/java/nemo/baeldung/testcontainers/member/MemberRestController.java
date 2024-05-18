package nemo.baeldung.testcontainers.member;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberRestController {
	private final MemberRepository memberRepository;

	@GetMapping("/members")
	public List<Member> members() {
		return memberRepository.findAll();
	}

	@PostMapping("/api/members")
	public String crateMember(@RequestBody MemberCreateRequest request){
		memberRepository.save(new Member(null, request.getName()));
		return "OK";
	}
}

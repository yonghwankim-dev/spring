package com.nemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

	private final MemberService memberService;

	@PostMapping("/member")
	public void saveMember(@RequestBody MemberSaveRequest request) {
		Member member = request.toEntity();
		memberService.save(member);
	}

	@GetMapping("/member")
	public Member getMember(@RequestParam Long memberId) {
		return memberService.getMember(memberId);
	}
}

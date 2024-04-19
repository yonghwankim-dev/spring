package nemo.demo.test_container;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nemo.test_container.Member;
import nemo.test_container.MemberService;

@SpringBootTest
class MemberServiceTest{

	@Autowired
	private MemberService memberService;

	@Test
	void save(){
	    // given
		Member member = Member.builder()
			.name("홍길동")
			.build();

		// when
		Member saveMember = memberService.save(member);

		// then
		Assertions.assertThat(member.getId()).isEqualTo(saveMember.getId());
	}

	@Test
	void fineOne(){
		// given
		Member member = Member.builder()
			.name("홍길동")
			.build();
		Member saveMember = memberService.save(member);

		// when
		Member fineMember = memberService.fineOne(saveMember.getId());

		// then
		Assertions.assertThat(saveMember.getId()).isEqualTo(fineMember.getId());
	}
}

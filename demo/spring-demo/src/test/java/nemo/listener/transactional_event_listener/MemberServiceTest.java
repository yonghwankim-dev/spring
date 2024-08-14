package nemo.listener.transactional_event_listener;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberSignupMessageRepository memberSignupMessageRepository;

	@AfterEach
	 void tearDown(){
		memberRepository.deleteAll();
		memberSignupMessageRepository.deleteAll();
	}

	@DisplayName("사용자는 회원가입후 가입 축하 메시지를 받고 메시지는 저장된다")
	@Test
	void signup(){
	    // given

	    // when
	    memberService.signup("kim", true);
	    // then
		Assertions.assertThat(memberRepository.findByName("kim")).isNotNull();
		Assertions.assertThat(memberSignupMessageRepository.findAll()).hasSize(1);
	}

	@DisplayName("사용자는 회원가입하였지만 이메일 인증을 수행하지 않아 알림 축하 메시지를 받지 않는다")
	@Test
	void signup_whenIsVerifiedIsFalse_thenNotEvent(){
	    // given

	    // when
	    memberService.signup("kim", false);
	    // then
		Assertions.assertThat(memberRepository.findByName("kim")).isNotNull();
		Assertions.assertThat(memberSignupMessageRepository.findAll()).hasSize(0);
	}
}

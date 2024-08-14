package nemo.event_listener;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	@DisplayName("사용자는 회원가입하고 회원가입 메시지를 받는다")
	@Test
	void signup(){
	    // given

	    // when
	    memberService.signup("kim");
	    // then

	}
}

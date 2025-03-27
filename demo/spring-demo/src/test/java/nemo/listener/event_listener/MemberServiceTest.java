package nemo.listener.event_listener;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nemo.listener.event_listener.MemberService;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private TeamMemberRepository teamMemberRepository;

	@DisplayName("사용자는 회원가입하고 회원가입 메시지를 받는다")
	@Test
	void signup(){
	    // given
		teamRepository.save(new Team("teamA"));
	    // when
		Member saveMember = memberService.signup("kim", "teamA");
		// then
		assertThat(memberRepository.findAll()).hasSize(1);
		assertThat(teamMemberRepository.findAllByMemberId(saveMember.getId())).hasSize(1);
	}

	@DisplayName("사용자는 회원의 팀을 제거한다")
	@Test
	void deleteTeamMember(){
	    // given
		teamRepository.save(new Team("teamA"));
		Member member = memberService.signup("kim", "teamA");
		// when
	    memberService.deleteTeamMember(member.getId(), "teamA");
	    // then
	    assertThat(teamMemberRepository.findAllByMemberId(member.getId())).hasSize(0);
	}
}

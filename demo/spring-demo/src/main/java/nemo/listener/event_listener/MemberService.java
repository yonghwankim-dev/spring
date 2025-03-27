package nemo.listener.event_listener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;
	private final TeamRepository teamRepository;
	private final TeamMemberRepository teamMemberRepository;
	private final ApplicationEventPublisher eventPublisher;
	@Transactional
	public Member signup(String name, String teamName) {
		Member member = new Member(name);
		Team team = teamRepository.findByName(teamName).orElseThrow();
		TeamMember teamMember = new TeamMember(member, team);

		memberRepository.save(member);
		teamMemberRepository.save(teamMember);
		eventPublisher.publishEvent(new MemberSignupEvent(member.getId()));
		log.info("end signup service");
		return member;
	}

	@Transactional
	public void deleteTeamMember(Long memberId, String teamName) {
		Member member = memberRepository.findById(memberId).orElseThrow();
		log.info("member is {}", member);
		teamMemberRepository.deleteByMemberIdAndTeamName(memberId, teamName);
		eventPublisher.publishEvent(new TeamDeleteEvent(memberId));
	}
}

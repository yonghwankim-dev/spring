package nemo.event_listener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;
	private final ApplicationEventPublisher eventPublisher;
	@Transactional
	public void signup(String name) {
		Member member = new Member(name);
		memberRepository.save(member);
		eventPublisher.publishEvent(new MemberSignupEvent(member.getName()));
		log.info("end signup service");
	}
}

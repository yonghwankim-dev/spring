package nemo.listener.transactional_event_listener;

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
	private final ApplicationEventPublisher eventPublisher;
	@Transactional
	public void signup(String name, boolean isVerified) {
		Member member = new Member(name, isVerified);
		memberRepository.save(member);
		eventPublisher.publishEvent(new MemberSignupEvent(member.getName(), member.isEmailVerified()));
		log.info("end signup service");
	}
}

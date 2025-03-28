package nemo.listener.event_listener;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsoleSignupMessageService implements SignupMessageService {
	private final MemberRepository memberRepository;

	@Override
	@Transactional
	public void sendSignupMessage(Long memberId) {
		Member fidnMember = memberRepository.findById(memberId).orElseThrow();
		log.info("{}, congratulations on your membership.", fidnMember.getName());
		log.info("findMember is {}", fidnMember);
	}
}

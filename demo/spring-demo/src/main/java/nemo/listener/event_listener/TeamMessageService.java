package nemo.listener.event_listener;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamMessageService {
	private final MemberRepository memberRepository;
	@Transactional
	public void sendDeleteMessage(TeamDeleteEvent event) {
		Member member = memberRepository.findById(event.getMemberId()).orElseThrow();
		log.info("member is {}", member);
	}
}

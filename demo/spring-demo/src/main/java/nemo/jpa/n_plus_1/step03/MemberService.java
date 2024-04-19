package nemo.jpa.n_plus_1.step03;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;


	@Transactional
	public Member save(Member member){
		return memberRepository.save(member);
	}
}

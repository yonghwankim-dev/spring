package chap05_01.spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//특이점
//스프링이 검색해서 빈으로 등록가능하도록 @Component 애노테이션을 설정한다.
@Component
public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;

	public MemberRegisterService() {
	}
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}

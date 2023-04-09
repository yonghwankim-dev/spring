package chap05_01.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//특이점
//스프링이 검색해서 빈으로 등록가능하도록 @Component 애노테이션을 설정한다.
@Component
public class ChangePasswordService {

	@Autowired
	private MemberDao memberDao;

	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();

		member.changePassword(oldPwd, newPwd);

		memberDao.update(member);
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}

package chap04_02.spring.before;

import chap04_02.spring.Member;
import chap04_02.spring.MemberDao;
import chap04_02.spring.MemberNotFoundException;

public class ChangePasswordService {
	
	// 특이점
	// @Autowired 애노테이션 적용 이전
	private MemberDao memberDao;
	
	public void changePassword(String email, String oldPwd, String newPwd)
	{
		Member member = memberDao.selectByEmail(email);
		if(member==null)
		{
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
	}
	
	public void setMemberDao(MemberDao memberDao)
	{
		this.memberDao = memberDao;
	}
}

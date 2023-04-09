package chap04_02.spring.before;

import java.time.LocalDateTime;

import chap04_02.spring.DuplicateMemberException;
import chap04_02.spring.Member;
import chap04_02.spring.MemberDao;
import chap04_02.spring.RegisterRequest;

public class MemberRegisterService {
	// 특이점
	// @Autowired 적용 이전
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) 
	{
		this.memberDao = memberDao;
	}
	
	public Long regist(RegisterRequest req)
	{
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member!=null)
		{
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
										req.getEmail(),
										req.getPassword(),
										req.getName(),
										LocalDateTime.now()
									);
		memberDao.insert(newMember);
		return newMember.getId();
	}
	
}

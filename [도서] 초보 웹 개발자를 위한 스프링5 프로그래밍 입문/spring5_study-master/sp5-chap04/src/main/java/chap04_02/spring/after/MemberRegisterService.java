package chap04_02.spring.after;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import chap04_02.spring.DuplicateMemberException;
import chap04_02.spring.Member;
import chap04_02.spring.MemberDao;
import chap04_02.spring.RegisterRequest;

public class MemberRegisterService {
	// 특이점
	// @Autowired 애노테이션 적용
	@Autowired
	private MemberDao memberDao;
	
	// 특이점
	// @Autowired 애노테이션을 적용하여 공백 생성자를 생성하여 자동 의존 주입을 수행한다.
	public MemberRegisterService()
	{
		
	}
	
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

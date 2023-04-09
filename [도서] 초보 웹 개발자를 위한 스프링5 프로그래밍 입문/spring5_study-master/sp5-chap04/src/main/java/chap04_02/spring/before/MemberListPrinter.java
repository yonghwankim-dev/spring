package chap04_02.spring.before;

import java.util.Collection;

import chap04_02.spring.Member;
import chap04_02.spring.MemberDao;
import chap04_02.spring.MemberPrinter;


public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter()
	{
		
	}
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) 
	{
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() 
	{
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m->printer.print(m));
	}

	// 특이점
	// @Autowired 적용 이전
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	// 특이점
	// @Autowired 적용 이전
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}

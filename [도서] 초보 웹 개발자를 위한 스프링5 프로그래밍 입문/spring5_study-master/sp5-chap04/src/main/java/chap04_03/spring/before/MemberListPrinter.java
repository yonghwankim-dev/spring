package chap04_03.spring.before;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import chap04_03.spring.Member;
import chap04_03.spring.MemberDao;
import chap04_03.spring.MemberPrinter;


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

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	// 특이점
	// @Qualifier 애노테이션 적용 전
	// @Qualifier 애노테이션을 적용하지 않았기 때문에 파라미터의 한정자 이름은 "printer"이다.
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}

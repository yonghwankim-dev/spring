package chap04_04.spring.after;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import chap04_04.spring.Member;
import chap04_04.spring.MemberDao;
import chap04_04.spring.MemberPrinter;


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
	// @Qualifier 애노테이션 적용, 스프링이 자동 주입시 memberPrinter2() 메소드로 한정하게됨
	@Autowired
	@Qualifier("summaryPrinter")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}

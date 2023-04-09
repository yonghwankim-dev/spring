package chap04_02.spring.after;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

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
	// @Autowired 애노테이션 적용
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	// 특이점
	// @Autowired 애노테이션 적용
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}

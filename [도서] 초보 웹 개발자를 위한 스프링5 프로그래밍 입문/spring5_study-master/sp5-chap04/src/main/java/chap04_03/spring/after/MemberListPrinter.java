package chap04_03.spring.after;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
	// @Qualifier 애노테이션의 값을 printer로 설정하여 자동 의존 주입
	// 과정에서 한정자 값이 "printer"인 빈 설정 메소드를 대상으로 
	// 의존 객체를 생성한다.
	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}
	public MemberPrinter getPrinter() {
		return printer;
	}	
}

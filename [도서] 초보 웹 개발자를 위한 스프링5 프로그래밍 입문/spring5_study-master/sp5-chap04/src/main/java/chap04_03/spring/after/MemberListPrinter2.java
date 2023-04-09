package chap04_03.spring.after;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import chap04_03.spring.Member;
import chap04_03.spring.MemberDao;
import chap04_03.spring.MemberPrinter;


public class MemberListPrinter2 {
	@Autowired
	private MemberDao memberDao;
	
	// 특이점
	// @Qualifier 애노테이션의 값을 mprinter로 설정한다
	// printer 빈 설정 메서드를 대상으로 자동 의존 주입을 수행하는 것이 아닌 mprinter로 설정한 
	// printer2 빈 설정 메서드를 대상으로 자동 의존 주입을 수행한다.
	@Autowired
	@Qualifier("mprinter")
	private MemberPrinter printer;
	
	public MemberListPrinter2()
	{
		
	}
	
	public MemberListPrinter2(MemberDao memberDao, MemberPrinter printer) 
	{
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() 
	{
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m->printer.print(m));
	}


	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

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

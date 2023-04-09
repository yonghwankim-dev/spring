package chap04_04.spring.after;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import chap04_04.spring.Member;
import chap04_04.spring.MemberDao;
import chap04_04.spring.MemberPrinter;

public class MemberInfoPrinter {
	private MemberDao memDao;
	private MemberPrinter printer;
	
	public void printMemberInfo(String email)
	{
		Member member = memDao.selectByEmail(email);
		if(member==null)
		{
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	

	@Autowired
	public void setMemDao(MemberDao memDao) {
		this.memDao = memDao;
	}
	// 특이점
	// @Qualifier 애노테이션 적용, 스프링이 자동 주입시 memberPrinter1() 메소드로 한정하게됨
	@Autowired	
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	
}

package chap04_03.spring.after;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import chap04_03.spring.Member;
import chap04_03.spring.MemberDao;
import chap04_03.spring.MemberPrinter;

public class MemberInfoPrinter2 {
	@Autowired
	private MemberDao memDao;
	// 특이점
	// @Qualifier 애노테이션을 설정하지 않았기 때문에 printer 객체의 한정자 값은 "printer"이다.
	@Autowired
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

	public void setMemDao(MemberDao memDao) {
		this.memDao = memDao;
	}
	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

	public MemberDao getMemDao() {
		return memDao;
	}

	public MemberPrinter getPrinter() {
		return printer;
	}
	
	
}

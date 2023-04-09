package chap04_04.spring.before;

import org.springframework.beans.factory.annotation.Autowired;

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
	
	// 특이점
	// @Qualifier 애노테이션 삭제
	@Autowired
	public void setMemDao(MemberDao memDao) {
		this.memDao = memDao;
	}
	@Autowired	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	
}

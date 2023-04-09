package chap04_02.spring.after;

import org.springframework.beans.factory.annotation.Autowired;

import chap04_02.spring.Member;
import chap04_02.spring.MemberDao;
import chap04_02.spring.MemberPrinter;

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
	// @Autowired 애노테이션 적용
	@Autowired
	public void setMemDao(MemberDao memDao) {
		this.memDao = memDao;
	}
	
	// 특이점
	// @Autowired 애노테이션 적용
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	
}

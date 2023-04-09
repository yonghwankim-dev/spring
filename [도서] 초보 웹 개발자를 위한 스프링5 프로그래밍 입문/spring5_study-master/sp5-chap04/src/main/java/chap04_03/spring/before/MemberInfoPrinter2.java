package chap04_03.spring.before;

import org.springframework.beans.factory.annotation.Autowired;

import chap04_03.spring.Member;
import chap04_03.spring.MemberDao;
import chap04_03.spring.MemberPrinter;

public class MemberInfoPrinter2 {
	private MemberDao memDao;
	// 특이점
	// @Autowired 애노테이션도 @Qualifier 애노테이션이 없으면 필드나 파라미터 이름을 한정자로 사용한다.
	// 현재 MemberInfoPrinter2 클래스의 필드중 MemberPrinter 클래스 타입을 가지는 의존 객체의 필드명은 printer이다.
	// printer 필드에 일치하는 빈이 두 개 이상 존재(printer, printer2 in AppCtx2)하면 한정자로 필드 이름인 "printer"를 사용한다.
	// 즉, @Qualifier 애노테이션을 설정하지 않았기 때문에 스프링 설정 클래스(AppCtx2)에서 빈 설정 메서드로 printer 빈 설정 메서드를
	// 대상으로 자동 의존 주입을 수행한다.
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

	@Autowired
	public void setMemDao(MemberDao memDao) {
		this.memDao = memDao;
	}
	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	
}

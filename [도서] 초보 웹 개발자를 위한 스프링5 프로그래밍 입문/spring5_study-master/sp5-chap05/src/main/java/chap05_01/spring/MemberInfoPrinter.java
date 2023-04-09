package chap05_01.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//특이점
//스프링이 검색해서 빈으로 등록가능하도록 @Component 애노테이션을 설정한다. 속성값 추가
@Component("infoPrinter")
public class MemberInfoPrinter {

	private MemberDao memDao;
	private MemberPrinter printer;

	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}

	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

}

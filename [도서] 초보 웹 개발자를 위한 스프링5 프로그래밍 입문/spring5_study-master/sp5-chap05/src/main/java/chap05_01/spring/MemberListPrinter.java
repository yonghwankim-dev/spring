package chap05_01.spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 특이점
// @Component 애노테이션 설정 및 속성값 "listPrinter" 설정, 빈 이름은 listPrinter
@Component("listPrinter")
public class MemberListPrinter {

	private MemberDao memberDao;
	private MemberPrinter printer;

	public MemberListPrinter() {
	}
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}

	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Autowired
	public void setMemberPrinter(MemberSummaryPrinter printer) {
		this.printer = printer;
	}
}

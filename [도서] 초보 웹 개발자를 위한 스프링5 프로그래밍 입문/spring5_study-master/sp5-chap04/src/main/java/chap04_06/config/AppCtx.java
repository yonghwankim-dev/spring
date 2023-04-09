package chap04_06.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_06.spring.ChangePasswordService;
import chap04_06.spring.MemberDao;
import chap04_06.spring.MemberInfoPrinter;
import chap04_06.spring.MemberListPrinter;
import chap04_06.spring.MemberPrinter;
import chap04_06.spring.MemberRegisterService;
import chap04_06.spring.MemberSummaryPrinter;
import chap04_06.spring.VersionPrinter;

@Configuration
public class AppCtx {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		return new ChangePasswordService();
	}
	
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		// 특이점
		// 설정 클래스에서 명시적으로 memberPrinter2 빈 주입
		infoPrinter.setPrinter(memberPrinter2());
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}

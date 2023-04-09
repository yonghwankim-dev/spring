package chap04_05.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_05.spring.ChangePasswordService;
import chap04_05.spring.MemberDao;
import chap04_05.spring.MemberInfoPrinter;
import chap04_05.spring.MemberListPrinter;
import chap04_05.spring.MemberPrinter;
import chap04_05.spring.MemberRegisterService;
import chap04_05.spring.MemberSummaryPrinter;
import chap04_05.spring.VersionPrinter;

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

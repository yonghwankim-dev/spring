package chap05_01.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import chap05_01.spring.MemberPrinter;
import chap05_01.spring.MemberSummaryPrinter;
import chap05_01.spring.VersionPrinter;


// 변경점
// @Component 애노테이션을 붙인 클래스를 스캔해서 스프링 빈으로 등록하려면 설정 클래스에
// @ComponentScan 애노테이션을 적용해야 한다.
@Configuration
@ComponentScan(basePackages = {"chap05_01.spring"})
public class AppCtx {
	
	/* 삭제 가능
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
	*/
	
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
	
	/* 삭제 가능
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		return infoPrinter;
	}
	*/
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}

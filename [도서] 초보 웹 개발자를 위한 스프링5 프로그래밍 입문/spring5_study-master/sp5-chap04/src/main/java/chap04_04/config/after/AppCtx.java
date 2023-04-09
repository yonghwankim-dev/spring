package chap04_04.config.after;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_04.spring.ChangePasswordService;
import chap04_04.spring.MemberDao;
import chap04_04.spring.MemberPrinter;
import chap04_04.spring.MemberRegisterService;
import chap04_04.spring.MemberSummaryPrinter;
import chap04_04.spring.VersionPrinter;
import chap04_04.spring.after.MemberInfoPrinter;
import chap04_04.spring.after.MemberListPrinter;


@Configuration
public class AppCtx{

	@Bean
	public MemberDao memberDao() 
	{
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc()
	{
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc()
	{
		ChangePasswordService pwdSvc = new ChangePasswordService();
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter listPrinter()
	{
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter()
	{
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter()
	{
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
	
	// 특이점
	// @Qualifier 애노테이션 적용, 한정자 값 "printer"로 설정
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1()
	{
		return new MemberPrinter();
	}

	// 특이점
	// @Qualifier 애노테이션 적용, 한정자 값 "summaryPrinter"로 설정
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2()
	{
		return new MemberSummaryPrinter();
	}
}
package chap04_04.config.before;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_04.spring.ChangePasswordService;
import chap04_04.spring.MemberDao;
import chap04_04.spring.MemberPrinter;
import chap04_04.spring.MemberRegisterService;
import chap04_04.spring.MemberSummaryPrinter;
import chap04_04.spring.VersionPrinter;
import chap04_04.spring.before.MemberInfoPrinter;
import chap04_04.spring.before.MemberListPrinter;


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
	// memberPrinter1 빈 설정 메소드와 memberPrinter2 빈 설정 메서드가
	// 리턴의 이름은 다르지만 MemberSummaryPrinter는 MemberPrinter 클래스의
	// 하위 클래스 이므로 자동 의존 주입 과정에서 중복을 일으키는 문제가 발생한다. 
	@Bean
	public MemberPrinter memberPrinter1()
	{
		return new MemberPrinter();
	}
	
	@Bean
	public MemberSummaryPrinter memberPrinter2()
	{
		return new MemberSummaryPrinter();
	}
}
package chap04_03.config.before;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_03.spring.ChangePasswordService;
import chap04_03.spring.MemberDao;
import chap04_03.spring.MemberPrinter;
import chap04_03.spring.MemberRegisterService;
import chap04_03.spring.VersionPrinter;
import chap04_03.spring.before.MemberInfoPrinter;
import chap04_03.spring.before.MemberListPrinter;

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
	// 빈 설정 메소드의 리턴 타입이 MemberPrinter이 2개이므로 자동 의존 주입 과정에서
	// 스프링이 빈을 한정할 수 없기 때문에 NoUniqueBeanDefinitionException이 발생한다.
	@Bean
	public MemberPrinter memberPrinter1()
	{
		return new MemberPrinter();
	}
	
	@Bean
	public MemberPrinter memberPrinter2()
	{
		return new MemberPrinter();
	}
}

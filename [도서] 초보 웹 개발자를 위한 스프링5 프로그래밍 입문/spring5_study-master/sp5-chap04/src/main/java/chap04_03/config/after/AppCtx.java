package chap04_03.config.after;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_03.spring.ChangePasswordService;
import chap04_03.spring.MemberDao;
import chap04_03.spring.MemberPrinter;
import chap04_03.spring.MemberRegisterService;
import chap04_03.spring.VersionPrinter;
import chap04_03.spring.after.MemberListPrinter;
import chap04_03.spring.after.MemberInfoPrinter;

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
		return new ChangePasswordService();
	}

	@Bean
	public MemberListPrinter listPrinter()
	{
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter()
	{
		return new MemberInfoPrinter();
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
	// @Qualifer 애노테이션을 사용하면 자동주입 가능한 빈이 2개 이상인 경우 자동 주입 대상 빈을 한정할 수 있다.
	// memberPrinter1() 메서드의 한정값은 "printer"로 지정된다.
	@Bean
	@Qualifier("printer")
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

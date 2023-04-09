package chap04_03.config.after;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_03.spring.ChangePasswordService;
import chap04_03.spring.MemberDao;
import chap04_03.spring.MemberPrinter;
import chap04_03.spring.MemberRegisterService;
import chap04_03.spring.VersionPrinter;
import chap04_03.spring.after.MemberInfoPrinter2;
import chap04_03.spring.after.MemberListPrinter2;

@Configuration
public class AppCtx2{
	
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
	public MemberListPrinter2 listPrinter()
	{
		return new MemberListPrinter2();
	}
	
	@Bean
	public VersionPrinter versionPrinter()
	{
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}

	@Bean
	public MemberPrinter printer()
	{
		return new MemberPrinter();
	}
	
	// 특이점
	// @Qualifier 애노테이션을 사용하여서 printer2 빈 설정 메서드의 한정자 이름은 "mprinter"이다.
	@Bean
	@Qualifier("mprinter")
	public MemberPrinter printer2()
	{
		return new MemberPrinter();
	}
	
	@Bean
	public MemberInfoPrinter2 infoPrinter()
	{
		return new MemberInfoPrinter2();
	}
}

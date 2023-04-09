package chap04_03.config.before;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_03.spring.ChangePasswordService;
import chap04_03.spring.MemberDao;
import chap04_03.spring.MemberPrinter;
import chap04_03.spring.MemberRegisterService;
import chap04_03.spring.VersionPrinter;
import chap04_03.spring.before.MemberInfoPrinter;
import chap04_03.spring.before.MemberInfoPrinter2;
import chap04_03.spring.before.MemberListPrinter;

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
	public MemberListPrinter listPrinter()
	{
		return new MemberListPrinter();
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
	// 빈 설정 메소드 printer, printer2 메서드는 리턴 타입이 MemberPrinter 클래스 타입으로 중복이 유발된다.
	// @Qualifier 애노테이션을 설정하지 않으면 빈의 이름을 한정자로 지정한다.
	@Bean
	public MemberPrinter printer()
	{
		return new MemberPrinter();
	}
	
	@Bean
	public MemberPrinter printer2()
	{
		return new MemberPrinter();
	}
	
	// 특이점
	// MemberInfoPrinter2 클래스의 필드인 MemberPrinter printer 의존 객체는 
	// @Autowired 애노테이션을 설정한 상태이지만 AppCtx2 클래스의 빈 설정 메서드에서
	// printer 의존 객체에 일치하는 빈이 2개 이상(printer, printer2) 존재한다.
	// 하지만 @Qualifier 애노테이션을 따로 지정하지 않으면 한정자의 이름은 필드나 파라미터 이름으로 설정된다.
	// 따라서 printer 의존 객체의 한정자 이름은 "printer"이다.
	@Bean
	public MemberInfoPrinter2 infoPrinter()
	{
		MemberInfoPrinter2 infoPrinter = new MemberInfoPrinter2();
		return infoPrinter;
	}
}

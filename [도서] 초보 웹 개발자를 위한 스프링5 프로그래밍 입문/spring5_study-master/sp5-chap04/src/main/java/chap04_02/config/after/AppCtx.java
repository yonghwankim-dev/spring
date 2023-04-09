package chap04_02.config.after;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_02.spring.MemberDao;
import chap04_02.spring.MemberPrinter;
import chap04_02.spring.VersionPrinter;
import chap04_02.spring.after.ChangePasswordService;
import chap04_02.spring.after.MemberInfoPrinter;
import chap04_02.spring.after.MemberListPrinter;
import chap04_02.spring.after.MemberRegisterService;

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
		// 특이점
		// @Autowired 애노테이션 적용하여 생성자에 MemberDao 객체를 수동 의존 주입할 필요 없음
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc()
	{
		// 특이점
		// @Autowired 애노테이션 적용
		return new ChangePasswordService();
	}

	@Bean
	public MemberListPrinter listPrinter()
	{
		// 특이점
		// @Autowired 애노테이션 적용하여 setter 메서드를 통하여 수동 의존 주입할 필요 없음
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter()
	{
		// 특이점
		// @Autowired 애노테이션 적용하여 setter 메서드를 통하여 수동 의존 주입할 필요 없음
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
	
	@Bean
	public MemberPrinter memberPrinter()
	{
		return new MemberPrinter();
	}
}

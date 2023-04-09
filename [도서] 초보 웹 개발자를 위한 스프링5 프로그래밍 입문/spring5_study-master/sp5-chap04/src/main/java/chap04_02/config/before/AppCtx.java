package chap04_02.config.before;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04_02.spring.MemberDao;
import chap04_02.spring.MemberPrinter;
import chap04_02.spring.VersionPrinter;
import chap04_02.spring.before.ChangePasswordService;
import chap04_02.spring.before.MemberInfoPrinter;
import chap04_02.spring.before.MemberListPrinter;
import chap04_02.spring.before.MemberRegisterService;

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
		// @Autowired 애노테이션 적용 이전
		MemberRegisterService memberRegisterService = new MemberRegisterService(memberDao());
		return memberRegisterService;
	}
	
	@Bean
	public ChangePasswordService changePwdSvc()
	{
		ChangePasswordService pwdSvc = new ChangePasswordService();
		// 특이점
		// @Autowired 애노테이션 적용 이전
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}

	@Bean
	public MemberListPrinter listPrinter()
	{
		MemberListPrinter memberListPrinter = new MemberListPrinter();
		// 특이점
		// @Autowired 애노테이션 적용 이전
		memberListPrinter.setMemberDao(memberDao());
		memberListPrinter.setPrinter(memberPrinter());
		return memberListPrinter;
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter()
	{
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		// 특이점
		// @Autowired 애노테이션 적용 이전
		infoPrinter.setMemDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
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
	
	@Bean
	public MemberPrinter memberPrinter()
	{
		return new MemberPrinter();
	}
}

package chap05_01.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap05_01.config.AppCtx;
import chap05_01.spring.ChangePasswordService;
import chap05_01.spring.DuplicateMemberException;
import chap05_01.spring.MemberInfoPrinter;
import chap05_01.spring.MemberListPrinter;
import chap05_01.spring.MemberNotFoundException;
import chap05_01.spring.MemberRegisterService;
import chap05_01.spring.RegisterRequest;
import chap05_01.spring.VersionPrinter;
import chap05_01.spring.WrongIdPasswordException;

/**
 * 5.1~5.3
 * 스프링이 검색해서 빈으로 등록할 수 있으려면 클래스에 @Component 애노테이션을 붙여야 한다.
 * 
 * 변경점 : 
 * 1. ChangePassowrdService 클래스, MemberRegisterService 클래스에 @Component 애노테이션 설정
 * 2. MemberInfoPrinter 클래스에 @Component 애노테이션 설정 및 속성값("infoPrinter") 설정, 빈이름은 "infoPrinter"
 * 3. MmeberListPrinter 클래스에 @Component 애노테이션 설정 및 속성값("listPrinter") 설정, 빈이름은 "listPrinter"
 * 4. AppCtx 스프링 설정 클래스에 @ComponentScan(basePackages = {"spring"}) 적용
 * 
 * @Component 애노테이션에 속성값을 추가하지 않을 시 빈이름은 클래스의 첫글자를 소문자로 바꾼 이름이다.
 * ex) ChangePasswordService의 빈 이름 : changePasswordService
 * 
 * 
 * 		
 *
 */

public class MainForSpring {

	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if (command.equals("list")) {
				processListCommand();
				continue;
			} else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			} else if (command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}
	}

	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}
		
// 		before
//		MemberRegisterService regSvc = 
//				ctx.getBean("memberRegSvc", MemberRegisterService.class);
		
// 		after
		MemberRegisterService regSvc = 
				ctx.getBean(MemberRegisterService.class);

		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
//		before
//		ChangePasswordService changePwdSvc =
//				ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		ChangePasswordService changePwdSvc =
				ctx.getBean(ChangePasswordService.class);		
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println("list");
		System.out.println("info 이메일");
		System.out.println("version");
		System.out.println();
	}

	private static void processListCommand() {
		// @Component 애노테이션 속성값으로 listPrinter로 설정하였으므로 변경할 필요 없음
		MemberListPrinter listPrinter = 
				ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void processInfoCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
		// @Component 애노테이션 속성값으로 listPrinter로 설정하였으므로 변경할 필요 없음
		MemberInfoPrinter infoPrinter = 
				ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = 
				ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}

}
package chap04_04.main.after;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import chap04_04.spring.ChangePasswordService;
import chap04_04.spring.DuplicateMemberException;
import chap04_04.spring.MemberNotFoundException;
import chap04_04.spring.MemberRegisterService;
import chap04_04.spring.RegisterRequest;
import chap04_04.spring.VersionPrinter;
import chap04_04.spring.WrongIdPasswordException;
import chap04_04.spring.after.MemberInfoPrinter;
import chap04_04.spring.after.MemberListPrinter;
import chap04_04.config.after.AppCtx;

/**
 * 예제목적
 * 상위 클래스(MemberPrinter)와 하위 클래스(MemberSuumaryPrinter) 타입의 빈 설정 메소드에
 * @Qualifier 애노테이션을 부여하여 스프링이 빈 메소드를 한정 지을 수 있게 한다.
 * 
 * config.after.AppCtx.java : memberPrinter1 메서드에 "printer" 한정자 값 부여
 * spring.after.MemberInfoPrinter.java : MemberPrinter 의존 객체에게 "printer" 한정자 값 부여
 * spring.after.MemberListPrinter.java : MemberPrinter 의존 객체에게 "summaryPrinter" 한정자값 부여
 * 
 * 실행결과
 * info, list 명령어 수행시 MemberPrinter 클래스의 print 메소드인지, MemberSummaryPrinter 클래스의 메소드인지
 * 확인이 가능하다.
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
		MemberRegisterService regSvc =
				ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if (!req.isPasswordEqualsToComfirmPassword()) {
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
		ChangePasswordService changePwdSvc = 
				ctx.getBean("changePwdSvc", ChangePasswordService.class);
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
		System.out.println("info 이메일");
		System.out.println("version");
		System.out.println();
	}

	private static void processListCommand() {
		MemberListPrinter listPrinter = 
				ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void processInfoCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
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

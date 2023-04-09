package chap04_03.main.after;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap04_03.spring.ChangePasswordService;
import chap04_03.spring.DuplicateMemberException;
import chap04_03.spring.MemberNotFoundException;
import chap04_03.spring.MemberRegisterService;
import chap04_03.spring.RegisterRequest;
import chap04_03.spring.VersionPrinter;
import chap04_03.spring.WrongIdPasswordException;
import chap04_03.spring.after.MemberInfoPrinter;
import chap04_03.spring.after.MemberListPrinter;
import chap04_03.config.after.AppCtx;

/**
 * 본 예제의 목적은 스프링 설정 클래스(AppCtx)의 자동 의존 주입이 가능한 빈이 2개 이상인 경우, 빈 설정 메서드에
 * @Qualifier 애노테이션을 적용하여 스프링의 자동 의존 주입 과정에서 빈을 지정할 수
 * 있도록 하는 것에 목적을 가지고 있다.
 * 
 * config.after.AppCtx.java : memberPrinter1() 메서드에 @Qualifier 애노테이션 설정 ("printer")
 * spring.after.MemberListPrinter.java : setPrinter() 메서드에 @Qualifier 애노테이션 설정("printer")
 * spring.after.MemberInfoPrinter.java : MemberPrinter 의존 객체의 setter 메서드에 @Qualifier 애노테이션 설정("printer")
 * 
 * 실행결과
 * 자동 의존 주입의 결과로 생성된 의존 객체는 싱글톤 객체의 특성을 가지고 있기 때문에
 * 객체 값을 출력한 결과 둘다 동일한 것을 알 수 있다. 이는 MemberListPrinter 클래스와
 * MemberInfoPrinter 클래스가 가지고 있는 MemberPrinter 타입의 필드 멤버의
 * 자동 의존 주입 과정에서 한정자 값이 "printer"인 빈 설정 메서드(memberPrinter1)를
 * 대상으로 수행하고 의존 객체를 수행했기 때문이다. 같은 대상의 빈 설정 메서드를 수행했기 때문에
 * 싱글톤 객체를 가질 수 있는 것을 확인 할 수 있었다.
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
		System.out.println("list");
		System.out.println("info 이메일");
		System.out.println("version");
		System.out.println();
	}

	private static void processListCommand() {
		MemberListPrinter listPrinter = 
				ctx.getBean("listPrinter", MemberListPrinter.class);
		
		System.out.println("MemberListPrinter.MemberPrinter : " + listPrinter.getPrinter());
		
		listPrinter.printAll();
	}

	private static void processInfoCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = 
				ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		
		System.out.println("MemberInfoPrinter.MemberPrinter : " + infoPrinter.getPrinter());
		infoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = 
				ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}

}

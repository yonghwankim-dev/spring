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
import chap04_03.spring.after.MemberInfoPrinter2;
import chap04_03.spring.after.MemberListPrinter2;
import chap04_03.config.after.AppCtx2;

/**
 * 스프링 설정 클래스(AppCtx2)에서 printer2 빈 설정 메서드에
 * @Qualifier 애노테이션을 "mprinter2"로 설정하였다.
 * 또한 MemberInfoPrinter2 클래스의 의존 객체인 printer 필드에 @Qualifier 애노테이션을 적용하여
 * 기존 printer 빈 설정 메서드가 아닌 printer2 빈 설정 메서드를 대상으로 자동 의존 주입을 수행하도록 하였다.
 * 
 * 
 * 본 예제의 목적은 스프링 설정 클래스의 의존 자동 주입시 MemberPrinter 클래스를 사용하는 의존 객체의
 * 자동 의존 주입을 "mprinter"를 설정한 printer2 빈 설정 메서드를 통하여 수행하는 것을 관찰한다.
 * 
 * config.after.AppCtx2.java : printer2 빈 설정 메서드에 @Qualifier 애노테이션 적용 (mprinter)
 * spring.after.MemberInfoPrinter2.java : MemberPrinter 타입의 필드멤버에 @Qualifier 애노테이션을 적용하지 않음
 * 											이런 경우 한정자 값은 파라미터 값이나 필드 멤버 값이 된다. (printer)
 * spring.after.MemberListPrinter2.java : MemberPrinter 타입의 필드멤버에 @Qulaifier 애노테이션을 적용한다. (mprinter)
 * 											해당 필드멤버에 자동 의존 주입 과정을 수행시 한정자 값이 mprinter 값을 한정 지어서 수행한다.
 * 
 * 실행결과
 * MemberInfoPrinter2 클래스의 MemberPrinter 타입의 printer 객체는 빈 설정 메서드에서 printer를 대상으로 의존 객체를 생성하고
 * MemberListPrinter2 클래스의 MemberPrinter 타입의 printer 객체는 빈 설정 메서드에서 mprinter를 대상으로 의존 객체를 생성한다.
 * 따라서 두 의존 객체는 싱글톤 객체가 아닌 다른 객체라는 것을 확인할 수 있다.
 *  
 * 서비스 수행 이상 없음.
 * 
 */
public class MainForSpring2 {

	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx2.class);
		
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
		MemberListPrinter2 listPrinter = 
				ctx.getBean("listPrinter", MemberListPrinter2.class);
		
		System.out.println("MemberListPrinter2.MemberPrinter : " + listPrinter.getPrinter());
		listPrinter.printAll();
	}

	private static void processInfoCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
		
		// 특이점
		// MemberInfoPrinter -> MemberInfoPrinter2 클래스로 변경
		MemberInfoPrinter2 infoPrinter = 
				ctx.getBean("infoPrinter", MemberInfoPrinter2.class);
		
		System.out.println("MemberInfoPrinter2.MemberPrinter : " + infoPrinter.getPrinter());
		
		infoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = 
				ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}

}

package chap04_03.main.before;

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
import chap04_03.spring.before.MemberInfoPrinter2;
import chap04_03.spring.before.MemberListPrinter;
import chap04_03.config.before.AppCtx2;

/**
 * 본 예제의 목적은 MemberInfoPrinter2의 MemberPrinter printer 의존 객체이자 필드멤버가
 * @Autowired 애노테이션을 설정한데 반해 스프링 설정 클래스(AppCtx2)에서 printer 필드에
 * 일치하는 빈이 두 개 이상 존재한다. @Qualifier 애노테이션이 따로 설정되지 않은 상황이므로
 * printer 필드의 한정자 이름은 "printer"를 사용한다.
 * 
 * 실행결과
 * NoUniqueBeanDefinitionException이 발생하지 않고 printer 빈 설정 메서드를
 * 대상으로 자동 의존 주입을 수행하여 서비스를 이상없이 수행한다.
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
		MemberListPrinter listPrinter = 
				ctx.getBean("listPrinter", MemberListPrinter.class);
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
		infoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = 
				ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}

}

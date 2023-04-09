package chap04_02.main.before;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap04_02.spring.DuplicateMemberException;
import chap04_02.spring.MemberNotFoundException;
import chap04_02.spring.RegisterRequest;
import chap04_02.spring.VersionPrinter;
import chap04_02.spring.WrongIdPasswordException;
import chap04_02.spring.before.ChangePasswordService;
import chap04_02.spring.before.MemberInfoPrinter;
import chap04_02.spring.before.MemberListPrinter;
import chap04_02.spring.before.MemberRegisterService;
import chap04_02.config.before.AppCtx;

/**
 * 본 예제의 목적은 @Autowired 애노테이션 적용 이전의 수동 의존 주입을 테스트하는 예제이다.
 * 
 * 본 예제의 주목할 점은 @Autowired 애노테이션을 의존 객체 또는 메소드에 적용하지 않았기 때문에
 * 스프링 설정 클래스(AppCtx)의 빈 설정 메서드(@Bean)에서 의존 주입하기 위한 의존 객체를 생성 시
 * 생성자나 setter 메서드를 통해서 의존 객체를 주입한다. 하지만 이러한 수동 의존 주입은
 * Bean 메서드에서 의존을 수동으로 주입해주어야 한다.
 * 
 * 실행결과
 * 실행 결과 이상 없음.
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

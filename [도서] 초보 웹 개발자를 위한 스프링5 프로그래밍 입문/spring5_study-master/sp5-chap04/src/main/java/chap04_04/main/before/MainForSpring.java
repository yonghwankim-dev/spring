package chap04_04.main.before;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap04_04.config.before.AppCtx;
import chap04_04.spring.ChangePasswordService;
import chap04_04.spring.DuplicateMemberException;
import chap04_04.spring.MemberNotFoundException;
import chap04_04.spring.MemberRegisterService;
import chap04_04.spring.RegisterRequest;
import chap04_04.spring.VersionPrinter;
import chap04_04.spring.WrongIdPasswordException;
import chap04_04.spring.before.MemberInfoPrinter;
import chap04_04.spring.before.MemberListPrinter;
/**
 * 
 * config.before.AppCtx.java의 내용을 보면
 * Bean 설정 메서드로 memberPrinter1() 메서드와 memberPrinter2() 메서드가 존재한다.
 * 그리고 두 메서드의 리턴타입은 MemberPrinter와 MemberSummaryPrinter 클래스 타입이다.
 * 하지만 MemberPrinter 클래스는 MemberSummaryPrinter 클래스의 상위 클래스이므로
 * 빈 설정 메서드는 자동 의존 주입 과정에서 어떤 빈을 주입해야 할지 알 수 없다.
 * 
 * config.before.AppCtx.java : 상위클래스(MemberPrinter)와 하위클래스(MemberSummaryPrinter) 타입에 대한
 * 								빈 설정 메서드를 생성함
 * spring.before.MemberInfoPrinter.java : MemberPrinter 의존 객체의 @Qualifier 애노테이션 삭제
 * spring.before.MemberListPrinter.java : MemberPrinter 의존 객체의 @Qualifier 애노테이션 삭제
 * 
 *  실행결과
 *  스프링 컨테이너는 MemberPrinter 타입의 두 의존객체에 자동 의존 주입을 수행 중 memberPrinter1 빈 설정 메서드와
 *  memberPrinter2 빈 설정 메소드 중 한정할 대상을 구분할 수 없어 NoUniqueBeanDefinitionException 예외를 발생시킨다.
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

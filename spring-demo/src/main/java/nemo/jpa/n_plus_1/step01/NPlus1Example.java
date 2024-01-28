package nemo.jpa.n_plus_1.step01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 즉시 로딩 N+1문제
 * - 팀 엔티티를 조회시 연관 엔티티인 회원 엔티티들을 조회하는 SQL을 팀 엔티티의 데이터 개수만큼 추가적으로 조회하는 문제입니다.
 * - 예를 들어 팀 엔티티 조회시 1개의 팀 조회 SQL이 발생하고 팀 엔티티의 데이터 개수는 2개라고 가정하면 회원을 조회하는 SQL이 2회 발생합니다.
 */
@SpringBootApplication
public class NPlus1Example implements ApplicationRunner {

	@Autowired
	private MemberService memberService;

	@Autowired
	private TeamService teamService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Team teamA = Team.builder()
			.name("팀A")
			.build();
		Team teamB = Team.builder()
			.name("팀B")
			.build();
		Member member1 = Member.builder()
			.name("김수일")
			.team(teamA)
			.build();
		Member member2 = Member.builder()
			.name("김수이")
			.team(teamA)
			.build();
		Member member3 = Member.builder()
			.name("김수삼")
			.team(teamB)
			.build();
		Member member4 = Member.builder()
			.name("김수사")
			.team(teamB)
			.build();
		teamService.save(teamA);
		teamService.save(teamB);
		memberService.save(member1);
		memberService.save(member2);
		memberService.save(member3);
		memberService.save(member4);

		teamA.addMember(member1);
		teamA.addMember(member2);
		teamB.addMember(member3);
		teamB.addMember(member4);
		teamService.save(teamA);
		teamService.save(teamB);

		System.out.println("===팀 전체 조회 시작===");
		List<Team> teams = teamService.findAll();
		System.out.println(teams);
		System.out.println("===팀 전체 조회 종료===");
		System.exit(0);
	}

	public static void main(String[] args) {
		SpringApplication.run(NPlus1Example.class, args);
	}
}

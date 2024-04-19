package nemo.jpa.n_plus_1.step03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * N+1문제 해결방법1 : fetch join 사용
 * - JPQL 사용 및 쿼리에 join fetch를 사용하여 팀 및 회원 엔티티를 한꺼번에 로딩합니다.
 * - 팀의 회원 연관 엔티티는 지연 로딩 전략으로 설정되어 있습니다.
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
		teamService.findAll();
		System.out.println("===팀 전체 조회 종료===");
		System.exit(0);
	}

	public static void main(String[] args) {
		SpringApplication.run(NPlus1Example.class, args);
	}
}

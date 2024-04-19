package nemo.jpa.n_plus_1.step02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

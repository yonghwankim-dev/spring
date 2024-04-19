package nemo.jpa.n_plus_1.step03;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TeamService {

	private final TeamRepository teamRepository;

	@Transactional
	public Team save(Team team){
		return teamRepository.save(team);
	}

	@Transactional(readOnly = true)
	public List<Team> findAll(){
		List<Team> teams = teamRepository.findAllWithMember();
		for (Team team : teams){
			List<Member> members = team.getMembers();
			System.out.printf("%s, 회원수:%d명%n", team, members.size());
		}
		return teams;
	}
}

package nemo.jpa.n_plus_1.step01;

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
		return teamRepository.findAll();
	}
}

package nemo.jpa.n_plus_1.step04;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@EntityGraph(value = "Team.withMembers")
	List<Team> findAllBy();
}

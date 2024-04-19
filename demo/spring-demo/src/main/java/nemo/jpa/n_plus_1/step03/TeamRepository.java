package nemo.jpa.n_plus_1.step03;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {
	@Query("select t from Team t join fetch t.members")
	List<Team> findAllWithMember();
}

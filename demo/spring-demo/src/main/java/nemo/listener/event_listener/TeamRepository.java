package nemo.listener.event_listener;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends JpaRepository<Team, Long> {
	@Query("select t from Team t where t.name = :name")
	Optional<Team> findByName(@Param("name") String name);

	@Modifying
	@Query("delete from Team t where t.name = :teamName")
	void deleteByName(@Param("teamName") String teamName);
}

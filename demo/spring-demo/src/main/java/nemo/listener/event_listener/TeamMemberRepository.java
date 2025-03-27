package nemo.listener.event_listener;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
	@Query("select tm from TeamMember tm where tm.member.id = :memberId")
	List<TeamMember> findAllByMemberId(@Param("memberId") Long memberId);

	@Modifying
	@Query("delete from TeamMember tm where tm.member.id = :memberId and tm.team.name = :teamName")
	void deleteByMemberIdAndTeamName(@Param("memberId") Long memberId, @Param("teamName") String teamName);

}

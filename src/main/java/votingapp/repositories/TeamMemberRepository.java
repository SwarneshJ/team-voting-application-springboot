package votingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import votingapp.models.TeamMember;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer> {
    List<TeamMember> findByMemberId(int id);

    TeamMember findByName(String name);
}

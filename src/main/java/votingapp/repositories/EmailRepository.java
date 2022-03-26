package votingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import votingapp.models.Email;

public interface EmailRepository extends JpaRepository<Email, String> {
}

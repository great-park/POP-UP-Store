package park.waiting.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.domain.user.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}

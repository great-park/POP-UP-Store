package park.waiting.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.app.user.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}

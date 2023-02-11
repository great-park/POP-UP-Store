package park.waiting.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.app.user.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

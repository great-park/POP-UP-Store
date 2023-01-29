package park.waiting.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.domain.user.entity.Customer;

public class CustomerRepository extends JpaRepository<Customer, Long> {
}
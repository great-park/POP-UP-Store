package park.waiting.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.app.user.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}

package park.waiting.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.domain.store.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

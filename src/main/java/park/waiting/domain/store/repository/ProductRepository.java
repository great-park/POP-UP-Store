package park.waiting.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.domain.store.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStoreId(Long storeId);
}

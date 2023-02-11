package park.waiting.app.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.app.store.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStoreId(Long storeId);
}

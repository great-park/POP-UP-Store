package park.waiting.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}

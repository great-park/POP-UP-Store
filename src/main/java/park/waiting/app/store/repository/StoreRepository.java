package park.waiting.app.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.app.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}

package park.waiting.app.posting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.app.posting.entity.Posting;

import java.util.List;
import java.util.Optional;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    Optional<Posting> findFirstByStoreIdAndCustomerIdOrderByIdDesc(Long storeId, Long customerId);

    Optional<Posting> findByIdAndActiveIsTrue(Long postingId);

    List<Posting> findAllByStoreIdAndActiveIsTrue(Long storeId);

    List<Posting> findAllByCustomerIdAndActiveIsTrue(Long customerId);
}

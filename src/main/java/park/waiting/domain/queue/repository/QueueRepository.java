package park.waiting.domain.queue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.domain.queue.entity.Waiting;
import park.waiting.domain.queue.status.WaitingStatus;

import java.util.List;
import java.util.Optional;

public interface QueueRepository extends JpaRepository<Waiting, Long> {

    List<Waiting> findAllByStoreIdAndQueueStatusIs(Long storeId, WaitingStatus waitingStatus);

    Optional<Waiting> findByCustomerIdAndQueueStatusIs(Long customerId, WaitingStatus waitingStatus);

    List<Waiting> findAllByStoreId(Long storeId);
}

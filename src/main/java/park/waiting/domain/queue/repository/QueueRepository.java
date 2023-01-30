package park.waiting.domain.queue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.domain.queue.entity.Queue;
import park.waiting.domain.queue.status.QueueStatus;

import java.util.List;
import java.util.Optional;

public interface QueueRepository extends JpaRepository<Queue, Long> {

    List<Queue> findAllByStoreIdAndQueueStatusIs(Long storeId, QueueStatus queueStatus);

    Optional<Queue> findByCustomerIdAndQueueStatusIs(Long customerId, QueueStatus queueStatus);

}

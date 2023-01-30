package park.waiting.domain.queue.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.common.constant.ErrorCode;
import park.waiting.common.exception.GeneralException;
import park.waiting.domain.queue.dto.QueueRequest;
import park.waiting.domain.queue.dto.QueueResponse;
import park.waiting.domain.queue.entity.Queue;
import park.waiting.domain.queue.repository.QueueRepository;
import park.waiting.domain.queue.status.QueueStatus;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueueServiceImpl implements QueueService{

    private final QueueRepository queueRepository;

    @Override
    @Transactional(readOnly = true)
    public List<QueueResponse> getByStoreId(Long storeId) {
        return queueRepository.findAllByStoreIdAndQueueStatusIs(storeId, QueueStatus.WAITING)
                .stream()
                .map(Queue::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public QueueResponse getByCustomerId(Long customerId) {
        return queueRepository.findByCustomerIdAndQueueStatusIs(customerId, QueueStatus.WAITING)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR))
                .toResponse();
    }

    @Override
    public QueueResponse appendTo(QueueRequest queueRequest) {
        return null;
    }
}

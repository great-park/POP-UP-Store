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
import park.waiting.domain.queue.exception.DuplicatedQueueAppendException;
import park.waiting.domain.queue.repository.QueueRepository;
import park.waiting.domain.queue.status.QueueStatus;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.store.repository.StoreRepository;
import park.waiting.domain.user.entity.Customer;
import park.waiting.domain.user.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueueServiceImpl implements QueueService{

    private final QueueRepository queueRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<QueueResponse> getByStoreId(Long storeId) {
        return queueRepository.findAllByStoreIdAndQueueStatusIs(storeId, QueueStatus.WAITING)
                .stream()
                .map(Queue::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public QueueResponse getByCustomerId(Long customerId) {
        return queueRepository.findByCustomerIdAndQueueStatusIs(customerId, QueueStatus.WAITING)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR))
                .toResponse();
    }

    @Override
    @Transactional
    public QueueResponse appendTo(QueueRequest queueRequest) {
        Customer customer = customerRepository.findById(queueRequest.getCustomerId())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        Store store = storeRepository.findById(queueRequest.getStoreId())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));

        Queue checkDuplicate = queueRepository.findByCustomerIdAndQueueStatusIs(customer.getId(), QueueStatus.WAITING)
                .orElseThrow(DuplicatedQueueAppendException::new);
        Queue queue = queueRepository.save(Queue.of(customer, store));
        return queue.toResponse();
    }
}

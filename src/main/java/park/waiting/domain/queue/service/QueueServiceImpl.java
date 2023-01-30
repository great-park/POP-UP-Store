package park.waiting.domain.queue.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.common.constant.ErrorCode;
import park.waiting.common.exception.GeneralException;
import park.waiting.domain.queue.dto.QueueRequest;
import park.waiting.domain.queue.dto.QueueResponse;
import park.waiting.domain.queue.entity.Waiting;
import park.waiting.domain.queue.exception.DuplicatedWaitingAppendException;
import park.waiting.domain.queue.repository.QueueRepository;
import park.waiting.domain.queue.status.WaitingStatus;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.store.repository.StoreRepository;
import park.waiting.domain.user.entity.Customer;
import park.waiting.domain.user.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueueServiceImpl implements QueueService {

    private final QueueRepository queueRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<QueueResponse> getByStoreId(Long storeId) {
        return queueRepository.findAllByStoreIdAndQueueStatusIs(storeId, WaitingStatus.WAITING)
                .stream()
                .map(Waiting::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public QueueResponse getByCustomerId(Long customerId) {
        return queueRepository.findByCustomerIdAndQueueStatusIs(customerId, WaitingStatus.WAITING)
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

        queueRepository.findByCustomerIdAndQueueStatusIs(customer.getId(), WaitingStatus.WAITING)
                .orElseThrow(DuplicatedWaitingAppendException::new);

        Long waitingCount = queueRepository.findAllByStoreId(store.getId())
                .stream()
                .filter(WaitingStatus::isWaiting)
                .count();

        Waiting waiting = queueRepository.save(Waiting.of(customer, store, waitingCount));
        return waiting.toResponse();
    }

    // waiting 상태의 queue 중에서 해당 queue 앞에 남은 queue의 수를 구한다.
    @Override
    @Transactional(readOnly = true)
    public int getLeftPeopleNumber(Long queueId) {
        Waiting waiting = queueRepository.findById(queueId)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        List<QueueResponse> list = getByStoreId(waiting.getStore().getId());
        return list.indexOf(waiting.toResponse());
    }

    @Override
    @Transactional
    public void moveForward(Long storeId) {
        // 대기열 중 한 팀이 done or cancel한 상황 -> 대기열 떙기기
        queueRepository.findAllByStoreId(storeId)
                .forEach(waiting -> queueRepository.save(waiting.moveForward()));
    }

    @Override
    @Transactional
    public QueueResponse done(Long queueId) {
        Waiting waiting = queueRepository.findById(queueId)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        waiting.done();

        moveForward(waiting.getStore().getId());

        return waiting.toResponse();
    }

    @Override
    @Transactional
    public QueueResponse cancel(Long queueId) {
        Waiting waiting = queueRepository.findById(queueId)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        waiting.cancel();

        moveForward(waiting.getStore().getId());

        return waiting.toResponse();
    }
}

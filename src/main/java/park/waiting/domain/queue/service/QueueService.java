package park.waiting.domain.queue.service;

import park.waiting.domain.queue.dto.QueueRequest;
import park.waiting.domain.queue.dto.QueueResponse;

import java.util.List;

public interface QueueService {

    List<QueueResponse> getByStoreId(Long storeId);

    QueueResponse getByCustomerId(Long customerId);

    QueueResponse appendTo(QueueRequest queueRequest);

    int getLeftPeopleNumber(Long queueId);

    void moveForward(Long storeId);

    QueueResponse done(Long queueId);

    QueueResponse cancel(Long queueId);
}

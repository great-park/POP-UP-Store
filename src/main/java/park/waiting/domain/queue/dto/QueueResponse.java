package park.waiting.domain.queue.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import park.waiting.domain.queue.status.QueueStatus;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class QueueResponse {

    private Long id;

    private String storeName;

    private String customerName;

    private String phoneNumber;

    private Long waitingCount;

    private QueueStatus queueStatus;
}

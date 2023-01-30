package park.waiting.domain.queue.dto;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class QueueFixRequest {
    private Long storeId;
}

package park.waiting.app.posting.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class PostingDeleteRequest {

    private Long customerId;

    private Long postingId;
}

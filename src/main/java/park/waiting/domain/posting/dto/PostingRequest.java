package park.waiting.domain.posting.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class PostingRequest {

    private Long customerId;

    private Long storeId;

    private String title;

    private String contents;
}

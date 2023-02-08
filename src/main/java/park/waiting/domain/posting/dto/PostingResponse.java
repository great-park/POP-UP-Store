package park.waiting.domain.posting.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class PostingResponse {

    private Long id;

    private Long customerId;

    private String customerName;

    private Long storeId;

    private String storeName;

    private String title;

    private String contents;
}

package park.waiting.domain.store.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class ProductResponse {

    private Long productId;

    private String name;

    private Integer price;

    private String description;
}

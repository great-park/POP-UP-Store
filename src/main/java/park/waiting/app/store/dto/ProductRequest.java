package park.waiting.app.store.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class ProductRequest {

    private Long storeId;

    private Long productId;

    private String name;

    private Integer price;

    private String description;
}

package park.waiting.domain.store.entity;

import lombok.*;
import park.waiting.common.entity.BaseEntity;
import park.waiting.domain.store.dto.ProductResponse;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    private String name;

    private Integer price;

    private String description;

    public ProductResponse toResponse() {
        return ProductResponse.builder()
                .productId(getId())
                .name(name)
                .price(price)
                .price(price)
                .description(description)
                .build();
    }
}

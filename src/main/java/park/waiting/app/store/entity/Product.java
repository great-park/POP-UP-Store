package park.waiting.app.store.entity;

import lombok.*;
import park.waiting.app.common.entity.BaseEntity;
import park.waiting.app.store.dto.ProductRequest;
import park.waiting.app.store.dto.ProductResponse;

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

    public void update(ProductRequest productRequest) {
        this.name = productRequest.getName();
        this.price = productRequest.getPrice();
        this.description = productRequest.getDescription();
    }
}

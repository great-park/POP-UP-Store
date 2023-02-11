package park.waiting.app.user.entity;

import lombok.*;
import park.waiting.app.common.entity.BaseEntity;
import park.waiting.app.user.dto.CustomerResponse;

import javax.persistence.Entity;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends BaseEntity {

    @Setter
    private String name;

    private String phoneNumber;

    public CustomerResponse toResponse() {
        return CustomerResponse.builder()
                .customerId(getId())
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}

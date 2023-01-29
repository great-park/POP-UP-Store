package park.waiting.domain.user.entity;

import lombok.*;
import park.waiting.common.entity.BaseEntity;
import park.waiting.domain.user.dto.CustomerResponse;

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

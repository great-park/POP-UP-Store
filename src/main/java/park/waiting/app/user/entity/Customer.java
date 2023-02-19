package park.waiting.app.user.entity;

import lombok.*;
import park.waiting.app.auth.entity.Authority;
import park.waiting.app.common.entity.BaseEntity;
import park.waiting.app.user.dto.CustomerResponse;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "customer_authority",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities;

    public CustomerResponse toResponse() {
        return CustomerResponse.builder()
                .customerId(getId())
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}

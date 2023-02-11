package park.waiting.app.user.entity;

import lombok.*;
import park.waiting.app.common.entity.BaseEntity;
import park.waiting.app.store.entity.Store;
import park.waiting.app.user.dto.ManagerResponse;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager extends BaseEntity {

    private String email;

    private String password;

    private String phoneNumber;

    private String name;

    @OneToOne
    @Setter
    private Store store;

    public ManagerResponse toResponse() {
        Long storeId = store != null ? store.getId() : null;
        return ManagerResponse.builder()
                .id(getId())
                .storeId(storeId)
                .email(email)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}

package park.waiting.app.store.entity;

import lombok.*;
import park.waiting.app.common.entity.BaseEntity;
import park.waiting.app.store.dto.StoreRequest;
import park.waiting.app.store.dto.StoreResponse;
import park.waiting.app.store.status.OpenStatus;

import javax.persistence.Entity;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Store  extends BaseEntity {

    private String name;

    private String phoneNumber;

    private OpenStatus openStatus;

    private String openHours;

    private String address;

    public StoreResponse toResponse() {
        return StoreResponse.builder()
                .storeId(getId())
                .name(name)
                .phoneNumber(phoneNumber)
                .openStatus(String.valueOf(openStatus))
                .openHours(openHours)
                .address(address)
                .build();
    }

    public void update(StoreRequest storeRequest) {
        this.name = storeRequest.getName();
        this.phoneNumber = storeRequest.getPhoneNumber();
        this.openStatus = storeRequest.getOpenStatus();
        this.openHours = storeRequest.getOpenHours();
        this.address = storeRequest.getAddress();
    }
}

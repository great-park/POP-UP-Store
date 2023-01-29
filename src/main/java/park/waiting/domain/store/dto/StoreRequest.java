package park.waiting.domain.store.dto;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreRequest {

    private Long managerId;

    private String name;

    private String phoneNumber;

    private String openStatus;

    private String openHours;

    private String address;

    public static StoreRequest of(Long managerId, StoreAddRequest storeAddRequest) {
        return StoreRequest.builder()
                .managerId(managerId)
                .name(storeAddRequest.getName())
                .phoneNumber(storeAddRequest.getPhoneNumber())
                .openStatus(storeAddRequest.getOpenStatus())
                .openHours(storeAddRequest.getOpenHours())
                .address(storeAddRequest.getAddress())
                .build();
    }
}

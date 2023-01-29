package park.waiting.domain.store.dto;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreAddRequest {

    private String name;

    private String phoneNumber;

    private String openStatus;

    private String openHours;

    private String address;

}

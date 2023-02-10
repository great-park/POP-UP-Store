package park.waiting.domain.store.dto;

import lombok.*;
import park.waiting.domain.store.status.OpenStatus;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreAddRequest {

    private String name;

    private String phoneNumber;

    private OpenStatus openStatus;

    private String openHours;

    private String address;

}

package park.waiting.app.user.dto;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    private String phoneNumber;
}

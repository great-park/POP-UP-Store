package park.waiting.app.user.dto;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerRequest {
    private String email;

    private String name;

    private String password;

    private String phoneNumber;

}

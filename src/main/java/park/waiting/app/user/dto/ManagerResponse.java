package park.waiting.app.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class ManagerResponse implements Serializable {
    private Long id;
    private Long storeId;

    private String email;

    private String phoneNumber;
    private String name;
}

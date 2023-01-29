package park.waiting.domain.user.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class CustomerResponse implements Serializable {

    private Long customerId;

    private String phoneNumber;

    private String name;
}

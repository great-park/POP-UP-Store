package park.waiting.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import park.waiting.common.constant.ErrorCode;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ApiDataResponse<T> extends ApiErrorResponse {

    private final T data;

    private ApiDataResponse(T data) {
        super(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.data = data;
    }

    public static <T> ApiDataResponse<T> of(T data) {
        return new ApiDataResponse<>(data);
    }

    public static <T> ApiDataResponse<T> empty() {
        return new ApiDataResponse<>(null);
    }

}

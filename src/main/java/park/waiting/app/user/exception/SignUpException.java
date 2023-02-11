package park.waiting.app.user.exception;

import park.waiting.app.common.constant.ErrorCode;

public class SignUpException extends RuntimeException{
    public SignUpException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}

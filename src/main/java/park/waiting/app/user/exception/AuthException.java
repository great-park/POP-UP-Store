package park.waiting.app.user.exception;

import park.waiting.app.common.constant.ErrorCode;

public class AuthException extends RuntimeException{
    public AuthException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}

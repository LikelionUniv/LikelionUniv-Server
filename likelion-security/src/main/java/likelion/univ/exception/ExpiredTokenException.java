package likelion.univ.exception;

import likelion.univ.exception.base.BaseException;

public class ExpiredTokenException extends BaseException {
    
    public ExpiredTokenException() {
        super(SecurityErrorCode.EXPIRED_TOKEN);
    }
}

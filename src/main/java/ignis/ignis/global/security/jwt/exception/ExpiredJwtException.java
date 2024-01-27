package ignis.ignis.global.security.jwt.exception;


import ignis.ignis.global.error.exception.BusinessException;
import ignis.ignis.global.error.exception.ErrorCode;

public class ExpiredJwtException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
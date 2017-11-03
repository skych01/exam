package sys.security;

import org.springframework.security.core.AuthenticationException;

/**
 * jwt验证失败
 */
public class InvalidJWTTokenException extends AuthenticationException {
    public InvalidJWTTokenException(String msg) {
        super(msg);
    }

    public InvalidJWTTokenException(String msg, Throwable t) {
        super(msg, t);
    }
}

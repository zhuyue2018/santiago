package com.santiago.commons.dto.exception;

/**
 * @author sean
 * @Description: ${TODO}
 * @date 19-3-26 下午4:32
 */
public class SecurityException extends RuntimeException {

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityException(Throwable cause) {
        super(cause);
    }
}

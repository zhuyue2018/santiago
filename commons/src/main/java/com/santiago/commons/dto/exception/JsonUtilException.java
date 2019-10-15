package com.santiago.commons.dto.exception;

public class JsonUtilException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JsonUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonUtilException(String message) {
        super(message);
    }

    public JsonUtilException(Throwable cause) {
        super(cause);
    }
}
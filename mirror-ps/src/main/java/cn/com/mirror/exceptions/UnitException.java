package cn.com.mirror.exceptions;

public class UnitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnitException(String message) {
        super(message);
    }

    public UnitException(String message, Throwable cause) {
        super(message, cause);
    }
}

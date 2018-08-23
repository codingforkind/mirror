package cn.com.mirror.exceptions;

/**
 * @author piggy
 * @description
 * @date 18-8-23
 */
public class ReflectException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ReflectException(String message) {
        super(message);
    }

    public ReflectException(String message, Throwable cause) {
        super(message, cause);
    }
}

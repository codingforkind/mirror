package cn.com.mirror.exceptions;

/**
 * @author piggy
 * @description
 * @date 18-8-24
 */
public class EdgeTouchException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public EdgeTouchException(String message) {
        super(message);
    }

    public EdgeTouchException(String message, Throwable cause) {
        super(message, cause);
    }
}

package cn.com.mirror.exceptions;

/**
 * @author piggy
 * @description
 * @date 18-8-23
 */
public class GenerateNodeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GenerateNodeException(String message) {
        super(message);
    }

    public GenerateNodeException(String message, Throwable cause) {
        super(message, cause);
    }
}

package cn.com.mirror.exceptions;

/**
 * @author piggy
 * @description
 * @date 18-8-23
 */
public class NodeGenerateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NodeGenerateException(String message) {
        super(message);
    }

    public NodeGenerateException(String message, Throwable cause) {
        super(message, cause);
    }
}

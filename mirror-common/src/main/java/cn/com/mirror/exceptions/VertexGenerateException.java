package cn.com.mirror.exceptions;

/**
 * @author piggy
 * @description
 * @date 18-8-24
 */
public class VertexGenerateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public VertexGenerateException(String message) {
        super(message);
    }

    public VertexGenerateException(String message, Throwable cause) {
        super(message, cause);
    }
}

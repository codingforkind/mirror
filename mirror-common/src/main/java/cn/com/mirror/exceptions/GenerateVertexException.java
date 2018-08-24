package cn.com.mirror.exceptions;

/**
 * @author piggy
 * @description
 * @date 18-8-24
 */
public class GenerateVertexException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GenerateVertexException(String message) {
        super(message);
    }

    public GenerateVertexException(String message, Throwable cause) {
        super(message, cause);
    }
}

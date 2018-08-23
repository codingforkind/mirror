package cn.com.mirror.exceptions;

public class ProjectException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProjectException(String message) {
        super(message);
    }

    public ProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}

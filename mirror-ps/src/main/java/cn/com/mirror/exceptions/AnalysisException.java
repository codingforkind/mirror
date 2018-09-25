package cn.com.mirror.exceptions;

public class AnalysisException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AnalysisException(String message) {
        super(message);
    }

    public AnalysisException(String message, Throwable cause) {
        super(message, cause);
    }
}

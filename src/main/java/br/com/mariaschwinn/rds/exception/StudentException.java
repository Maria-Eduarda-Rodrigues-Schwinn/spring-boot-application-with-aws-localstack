package br.com.mariaschwinn.rds.exception;

public class StudentException extends RuntimeException {

    private static final long serialVersionUID = -7737840351898585883L;

    public StudentException() {
    }

    public StudentException(String message) {
        super(message);
    }

    public StudentException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentException(Throwable cause) {
        super(cause);
    }

    public StudentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

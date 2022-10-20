package tyut.selab.schedule.exception;

public class RepetitiveRequestException extends RuntimeException{
    public RepetitiveRequestException() {
    }

    public RepetitiveRequestException(String message) {
        super(message);
    }

    public RepetitiveRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepetitiveRequestException(Throwable cause) {
        super(cause);
    }

    public RepetitiveRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

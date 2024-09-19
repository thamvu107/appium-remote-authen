package exceptions;

public class WaitForConditionException extends RuntimeException {

    public WaitForConditionException(String message) {
        super(message);
    }

    public WaitForConditionException(String message, Throwable cause) {
        super(message, cause);
    }
}

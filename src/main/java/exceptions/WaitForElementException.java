package exceptions;

public class WaitForElementException extends RuntimeException {

    public WaitForElementException(String message) {
        super(message);
    }

    public WaitForElementException(String message, Throwable cause) {
        super(message, cause);
    }
}

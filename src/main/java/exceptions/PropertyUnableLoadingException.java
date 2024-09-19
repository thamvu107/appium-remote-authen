package exceptions;

public class PropertyUnableLoadingException extends RuntimeException {

    public PropertyUnableLoadingException(final String message) {
        super(message);
    }

    public PropertyUnableLoadingException(final String message, final Exception ex) {
        super(message, ex);
    }
}

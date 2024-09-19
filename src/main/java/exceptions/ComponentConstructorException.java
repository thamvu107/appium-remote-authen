package exceptions;

public class ComponentConstructorException extends Exception {

  public ComponentConstructorException(String message) {
    super(message);
  }

  public ComponentConstructorException(String message, Exception ex) {
    super(message, ex);
  }
}

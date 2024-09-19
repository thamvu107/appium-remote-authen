package exceptions;

public class PlatformNotSupportException extends IllegalStateException {
  public PlatformNotSupportException(String message) {
    super(message);
  }
}

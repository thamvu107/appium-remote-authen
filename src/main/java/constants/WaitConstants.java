package constants;

public final class WaitConstants {

  // Time constants in milliseconds
  // implicit is global then it should be small value
  public static final long LONG_IMPLICIT_WAIT = 3_000L;
  public static final long SHORT_IMPLICIT_WAIT = 10_000L;

  public static final long APP_LAUNCHING_WAIT = 60_000L;
  public static final long LONG_EXPLICIT_WAIT = 15_000L;
  public static final long EXPLICIT_WAIT = 10_000;
  public static final long SHORT_EXPLICIT_WAIT = 5_000L;
  public static final long SWIPE_SHORT_EXPLICIT_WAIT = 100L;

  public static final long VERTICAL_MOVE_IN_MILLIS = 50L;
  public static final long DISPLAY_TITLE_CARD_WAIT = 90L;
  public static final long SHORT_FLUENT_WAIT = 1_000L;
  public static final long POLLING_EVERY = 100L;
  public static final long QUICK_PAUSE = 250L;

  // Private constructor to prevent instantiation
  private WaitConstants() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

}

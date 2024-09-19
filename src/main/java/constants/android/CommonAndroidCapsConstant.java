package constants.android;

import utils.propertyReader.PropertiesMap;

import java.io.File;
import java.time.Duration;
import java.util.Objects;

import static java.time.Duration.ofSeconds;

public final class CommonAndroidCapsConstant {
  // Private constructor to prevent instantiation
  private CommonAndroidCapsConstant() {
  }

  private static final PropertiesMap props = new PropertiesMap("deviceCapConfig/android/commonAndroidCaps.properties");
  public static final Duration UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT = ofSeconds(props.getIntProperty("uiautomator2ServerLaunchTimeout"));
  // 30_000ms
  public static final Duration UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT =
    ofSeconds(props.getIntProperty("uiautomator2ServerInstallTimeout")); // 20_000ms
  public static final Duration UIAUTOMATOR2_SERVER_READY_TIMEOUT = ofSeconds(props.getIntProperty("uiautomator2ServerReadTimeout"));
  // 240_000ms

  public static final Duration NEW_COMMAND_TIMEOUT = ofSeconds(props.getIntProperty("newCommandTimeout")); // 60 seconds
  public static final Duration ADB_EXEC_TIMEOUT = ofSeconds(props.getIntProperty("adbExecTimeout")); // 20_000ms
  public static final Duration ANDROID_INSTALL_TIMEOUT = ofSeconds(props.getIntProperty("androidInstallTimeout")); // 90_000ms
  public static final String APP_PACKAGE = props.getProperty("appPackage");
  public static final String APP_WAIT_PACKAGE = props.getProperty("appWaitPackage");
  public static final String APP_ACTIVITY = props.getProperty("appActivity");
  public static final String APP_WAIT_ACTIVITY = props.getProperty("appWaitActivity");
  public static final String APP_FILE_NAME = props.getProperty("app");

  public static final String APP_PATH =
    Objects.requireNonNull(CommonAndroidCapsConstant.class.getClassLoader().getResource("apps" + File.separator + APP_FILE_NAME))
      .getPath();

  public static final boolean APP_WAIT_FOR_LAUNCH = props.getBooleanProperty("appWaitForLaunch");
  public static final Duration APP_WAIT_DURATION = ofSeconds(props.getIntProperty("appWaitDuration")); // default = 20_000

  public static final int REMOTE_APPS_CACHE_LIMIT = props.getIntProperty("remoteAppsCacheLimit");
  public static final boolean AUTO_GRANT_PERMISSION = props.getBooleanProperty("autoGrantPermissions");

  public static final boolean ALLOW_DELAY_ADB = props.getBooleanProperty("allowDelayAdb");
  public static final boolean FULL_RESET = props.getBooleanProperty("fullReset");
  public static final boolean NO_RESET = props.getBooleanProperty("noReset");
}

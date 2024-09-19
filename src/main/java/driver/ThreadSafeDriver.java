package driver;

import enums.DeviceType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;

import java.util.Objects;


public class ThreadSafeDriver {


  private ThreadSafeDriver() {
  }

  private static final ThreadLocal<AppiumDriver> threadDriver = new ThreadLocal<>();

  public static void getDriver(PlatformType platformType, String configureFile) {

    if (Objects.isNull(getDriver())) {
      initDriver(platformType, configureFile);
    } else {
      getDriver();
    }
  }

  private static void setDriver(AppiumDriver driver) {
    if (Objects.nonNull(driver)) {
      threadDriver.set(driver);
    }
  }

  private static void initDriver(PlatformType platformType, String configureFile) {

    AppiumDriver driver = new DriverFactory(platformType, configureFile).createDriver();

    setDriver(driver);
  }

  public static AppiumDriver getDriver() {
    return threadDriver.get();
  }


  public static void closeDriver() {
    if (Objects.nonNull(getDriver())) {
      getDriver().quit();
//      setDriver(null);
      threadDriver.remove();
    }
  }
}

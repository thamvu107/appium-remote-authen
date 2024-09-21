package driver;

import enums.MobileRunModeType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;

import java.util.Objects;

import static config.factory.MobileRunModeConfigFactory.getConfig;


public class ThreadSafeDriver {


  private ThreadSafeDriver() {
  }

  private static final ThreadLocal<AppiumDriver> threadDriver = new ThreadLocal<>();

  public static void getDriver(PlatformType platformType, String configureFile) {

    if (Objects.isNull(getDriver())) {
      initDriver(getConfig().getMobileRunMode(), platformType, configureFile);
    } else {
      getDriver();
    }
  }

  private static void setDriver(AppiumDriver driver) {
    if (Objects.nonNull(driver)) {
      threadDriver.set(driver);
    }
  }

  private static void initDriver(MobileRunModeType mobileRunModeType, PlatformType platformType, String configureFile) {

//    AppiumDriver driver = new DriverFactory(getConfig().getMobileRunMode(), platformType, configureFile).createDriver();
    AppiumDriver driver = new DriverFactory(mobileRunModeType, platformType, configureFile).createDriver();

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

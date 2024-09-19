package driverFactory;

import constants.WaitConstants;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.ios.IOSDriver;
import lombok.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.SessionNotCreatedException;
import utils.WaitUtils;

import java.net.URL;


public class DriverFactoryV0 {
  private final URL serverURL;
  private final Capabilities caps;
  AppiumDriver driver;

  public DriverFactoryV0(@NonNull URL serverURL, @NonNull Capabilities caps) {
    this.serverURL = serverURL;
    this.caps = caps;
  }

  public AppiumDriver getDriver() {

    if (driver == null) {
      driver = createDriver();
    }

    return driver;
  }

  private AppiumDriver createDriver() {
    AppiumDriver driver;
    try {

      PlatformType platform = getCurrentPlatformFromCapabilities();

      switch (platform) {
        case ANDROID:
          try {
            driver = new AndroidDriver(serverURL, caps);
            break;
          } catch (SessionNotCreatedException ex) {
            // ERROR: first time start emulator then  throw exception since timeout Appium setting is set only 30_000ms( caps)
            // Error: Appium Settings app is not running after 30000ms
//                        throw new SessionNotCreatedException(ex.getMessage());
//                        //Retrying to create driver
            if (ex.getMessage().contains("Appium Settings app is not running after 30000ms")) {
//                            System.out.println("Appium Settings app is not running: " + ex.getMessage());
              System.out.println("Retry to create driver");
              driver = new AndroidDriver(serverURL, caps);
              break;
            }

          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        case IOS:
          driver = new IOSDriver(serverURL, caps);
          break;
        default:
          throw new PlatformNotSupportException("Platform " + caps.getPlatformName() + " is not supported");
      }

      new WaitUtils(driver).setImplicitWait(WaitConstants.SHORT_IMPLICIT_WAIT);

    } catch (Exception e) {
      throw new RuntimeException("Failed to create driver: " + e.getMessage(), e);
    }

    return driver;
  }

  private PlatformType getCurrentPlatformFromCapabilities() {
    String platformName = CapabilityHelpers.getCapability(caps, "platformName", String.class);
    return PlatformType.valueOf(platformName.toUpperCase());
  }
}

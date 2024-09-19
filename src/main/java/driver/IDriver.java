package driver;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;

import java.net.URL;

public interface IDriver {
  // AppiumDriver getDriver(DriverType driverType);
  AppiumDriver getDriver(URL serverURL, Capabilities caps);
}

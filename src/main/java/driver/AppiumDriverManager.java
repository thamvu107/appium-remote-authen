package driver;

import entity.ServerConfig;
import enums.DeviceType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

@Slf4j
public abstract class AppiumDriverManager {
 // public abstract AppiumDriver createDriver(URL serverURL, DeviceType deviceType, String ConfigureFile);
  public abstract AppiumDriver createDriver(ServerConfig serverConfig, DeviceType deviceType, String ConfigureFile);


//  public abstract void closeDriver(AppiumDriver driver);

  public void closeDriver(AppiumDriver driver) {
    if (driver != null) {
      try {
        driver.quit();
      } catch (Exception e) {
        log.atInfo().setMessage("Failed to quit driver").setCause(e).log();
        throw new RuntimeException(e);
      }
    }
  }
}

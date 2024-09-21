package driver.remote;

import driver.AppiumDriverManager;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SessionNotCreatedException;

@Slf4j
public class SeleniumGridAndroidDriverManager extends AppiumDriverManager {
  protected SeleniumGridAndroidDriverManager(AppiumClientConfig appiumClientConfig, BaseOptions<?> caps) {
    super(appiumClientConfig, caps);
  }

  @Override
  public AppiumDriver createDriver() {
    AppiumDriver driver = null;
    try {
      driver = new AndroidDriver(appiumClientConfig, caps);
    } catch (SessionNotCreatedException se) {
      if (se.getMessage().contains(
        "Could not start a new session. Possible causes are invalid address of the remote server or browser start-up failure. ")) {
        log.error("invalid address of the remote server. Double check the remote server address and authentication credentials: (" + appiumClientConfig.toString() + ")");
        throw new RuntimeException("invalid address of the remote server. Double check the remote server address and authentication credentials: (" + appiumClientConfig.toString() + ")" , se);
      }

    } catch (Exception e) {
      log.error("Failed to create Android Driver: ", e);
      throw new RuntimeException("Failed to create Android Driver: ", e);
    }
    if(driver == null) {
      throw new RuntimeException("Android Driver is null");
    }

    return driver;
  }


}

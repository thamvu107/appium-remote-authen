package driver.local;

import config.factory.LocalConfigFactory;
import driver.AppiumDriverManager;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SessionNotCreatedException;

import java.net.URL;

@Slf4j
public class LocalAndroidDriverManager extends AppiumDriverManager {
  protected LocalAndroidDriverManager(AppiumClientConfig appiumClientConfig, BaseOptions<?> caps) {
    super(appiumClientConfig, caps);
  }

  @Override
  public AppiumDriver createDriver() {
    AppiumDriver driver = null;
    try {
      driver = new AndroidDriver(appiumClientConfig, caps);

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

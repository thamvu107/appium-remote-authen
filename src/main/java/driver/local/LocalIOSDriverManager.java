package driver.local;

import driver.AppiumDriverManager;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.options.BaseOptions;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

@Slf4j
public class LocalIOSDriverManager extends AppiumDriverManager {
  protected LocalIOSDriverManager(AppiumClientConfig appiumClientConfig, BaseOptions<?> caps) {
    super(appiumClientConfig, caps);
  }

  @Override
  public AppiumDriver createDriver() {
    try {
      return new IOSDriver(appiumClientConfig, caps);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create IOS Driver: ", e);
    }
  }
}

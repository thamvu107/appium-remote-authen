package driver;

import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.options.BaseOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IOSDriverManager extends AppiumDriverManager {
  protected IOSDriverManager(AppiumClientConfig appiumClientConfig, BaseOptions<?> caps) {
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

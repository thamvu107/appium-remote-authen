package driver;

import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AppiumDriverManager {
  protected AppiumClientConfig appiumClientConfig;
  protected BaseOptions<?> caps;

  protected AppiumDriverManager( AppiumClientConfig appiumClientConfig,  BaseOptions<?> caps) {
    this.caps = caps;
    this.appiumClientConfig = appiumClientConfig;
  }

  public abstract AppiumDriver createDriver();
}

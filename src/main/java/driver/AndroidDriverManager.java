package driver;

import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AndroidDriverManager extends AppiumDriverManager {
  protected AndroidDriverManager(AppiumClientConfig appiumClientConfig, BaseOptions<?> caps) {
    super(appiumClientConfig, caps);
  }
  @Override
  public AppiumDriver createDriver() {
    try {
      return new AndroidDriver(appiumClientConfig, caps);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create Android Driver: ", e);
    }
  }

}

package driver.remote;

import config.factory.SeleniumGridConfigFactory;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;
import lombok.extern.slf4j.Slf4j;
import utils.AppiumClientConfigManager;
import utils.WaitUtils;

import java.util.Objects;

@Slf4j
public class SeleniumGridDriverFactory {

  private AppiumDriver driver;
  private final PlatformType platformType;
  protected AppiumClientConfig appiumClientConfig;

  protected BaseOptions<?> caps;

  public SeleniumGridDriverFactory(PlatformType platformType, BaseOptions<?> caps) {
    this.platformType = platformType;
    this.caps = caps;
    this.appiumClientConfig = AppiumClientConfigManager.getSeleniumGridConfig(SeleniumGridConfigFactory.getConfig());
  }

  public AppiumDriver createDriver() {
    if (Objects.isNull(driver)) {
      switch (platformType) {
        case ANDROID:
          driver = new SeleniumGridAndroidDriverManager(appiumClientConfig, caps).createDriver();
          break;
        case IOS:
          driver = new SeleniumGridIOSDriverManager(appiumClientConfig, caps).createDriver();
          break;
        default:
          throw new PlatformNotSupportException("Platform " + platformType + " is not supported");
      }

      new WaitUtils(driver).setImplicitWait(0L);
    }

    return driver;
  }

}

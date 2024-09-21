package driver.local;

import config.factory.LocalConfigFactory;
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
public class LocalDriverFactory {

  private AppiumDriver driver;
  private final PlatformType platformType;

  protected BaseOptions<?> caps;
//  private URL url;
  private AppiumClientConfig appiumClientConfig;

  public LocalDriverFactory( PlatformType platformType, BaseOptions<?> caps) {
    this.platformType = platformType;
    this.caps = caps;
//    this.url = LocalConfigFactory.getConfig().getUrl();
    this.appiumClientConfig = AppiumClientConfigManager.getLocalConfig(LocalConfigFactory.getConfig());
  }

  public AppiumDriver createDriver() {
    if (Objects.isNull(driver)) {
      switch (platformType) {
        case ANDROID:
          driver = new LocalAndroidDriverManager(appiumClientConfig, caps).createDriver();
          break;
        case IOS:
          driver = new LocalIOSDriverManager(appiumClientConfig, caps).createDriver();
          break;
        default:
          throw new PlatformNotSupportException("Platform " + platformType + " is not supported");
      }

      new WaitUtils(driver).setImplicitWait(0L);
    }

    return driver;
  }

}

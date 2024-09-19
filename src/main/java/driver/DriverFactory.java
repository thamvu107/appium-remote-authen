package driver;

import capabilities.CapabilitiesFactory;
import entity.ServerConfig;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.options.BaseOptions;
import lombok.extern.slf4j.Slf4j;
import utils.AppiumClientConfigManager;
import utils.ServerConfigUtil;
import utils.WaitUtils;

import java.util.Objects;

@Slf4j
public class DriverFactory {

  private AppiumDriver driver;
  private final PlatformType platformType;
  protected AppiumClientConfig appiumClientConfig;

  protected BaseOptions<?> caps;

  public DriverFactory(PlatformType platformType, String configureFile) {
    this.platformType = platformType;
    this.caps = new CapabilitiesFactory(configureFile).getCaps(platformType);
    ServerConfig serverConfig = new ServerConfigUtil().getServerConfig();
    this.appiumClientConfig = new AppiumClientConfigManager(serverConfig).getAppiumClientConfig();
  }

  public AppiumDriver createDriver() {
    if (Objects.isNull(driver)) {
      switch (platformType) {
        case ANDROID:
          driver = new AndroidDriver(appiumClientConfig, caps);
          break;
        case IOS:
          driver = new IOSDriver(appiumClientConfig, caps);
          break;
        default:
          throw new PlatformNotSupportException("Platform " + platformType + " is not supported");
      }

      new WaitUtils(driver).setImplicitWait(0L);
    }

    return driver;
  }

}

package driver;

import entity.ServerConfig;
import enums.DeviceType;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import utils.ServerURLUtil;
import utils.WaitUtils;

import java.util.Objects;

@Slf4j
public class DriverFactory {

  private ServerConfig serverConfig;
  private AppiumDriver driver;
  private final PlatformType platformType;

  private final DeviceType deviceType;

  private final String configureFile;

  public DriverFactory(PlatformType platformType, DeviceType deviceType, String configureFile) {
    this.platformType = platformType;
    this.deviceType = deviceType;
    this.configureFile = configureFile;
  }

  public AppiumDriver createDriver() {
    if (Objects.isNull(driver)) {

      this.serverConfig = ServerURLUtil.getServerConfig();

      switch (platformType) {
        case ANDROID:
          driver = new AndroidDriverManager().createDriver(serverConfig, deviceType, configureFile);
          break;

        case IOS:
          driver = new IOSDriverManager().createDriver(serverConfig, deviceType, configureFile);
          break;
        default:
          throw new PlatformNotSupportException("Platform " + platformType + " is not supported");
      }

      new WaitUtils(driver).setImplicitWait(10L);
    }

    return driver;
  }


}

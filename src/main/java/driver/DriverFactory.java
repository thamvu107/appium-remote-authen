package driver;

import capabilities.CapabilitiesFactory;
import config.SeleniumGridConfig;
import config.factory.SeleniumGridConfigFactory;
import driver.local.LocalDriverFactory;
import driver.remote.SeleniumGridDriverFactory;
import entity.ServerConfig;
import enums.MobileRunModeType;
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

  private MobileRunModeType mobileRunModeType;
  private final PlatformType platformType;

  protected BaseOptions<?> caps;

  public DriverFactory(MobileRunModeType mobileRunModeType, PlatformType platformType,
                       String configureFile) {
    this.mobileRunModeType = mobileRunModeType;
    this.platformType = platformType;
    this.caps = new CapabilitiesFactory(platformType, configureFile).getCaps();
  }

  public AppiumDriver createDriver() {
    switch (this.mobileRunModeType) {
      case SELENIUM_GRID:
        return new SeleniumGridDriverFactory(platformType, caps).createDriver();
      case LOCAL:
        return new LocalDriverFactory(platformType, caps).createDriver();
      default:
        throw new PlatformNotSupportException("Platform " + platformType + " is not supported");
    }
  }

}

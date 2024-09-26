package capabilities;

import enums.PlatformType;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.options.BaseOptions;
import utils.propertyReader.PropertiesMap;

public class CapabilitiesFactory {
  private final PlatformType platformType;
  private final String configureFile;
  private final PropertiesMap propertiesMap;

  public CapabilitiesFactory(PlatformType platformType, String configureFile) {
    this.platformType = platformType;
    this.configureFile = configureFile;
    this.propertiesMap = new PropertiesMap(configureFile);
  }

  public BaseOptions<?> getCaps() {
    return platformType == PlatformType.IOS ? getIOSCaps() : getAndroidCaps();
  }

  public  UiAutomator2Options getAndroidCaps() {

    boolean isRealDevice = propertiesMap.getBooleanProperty("isRealDevice");
    AndroidCapabilitiesManager androidCaps = new AndroidCapabilitiesManager(configureFile);

    return isRealDevice ? androidCaps.getRealMobileCaps(propertiesMap) : androidCaps.getEmulatorCaps(propertiesMap);
  }

  public  XCUITestOptions getIOSCaps() {

    IOSCapabilitiesManager iosCaps = new IOSCapabilitiesManager(configureFile);
    boolean isRealDevice = propertiesMap.getBooleanProperty("isRealDevice");

    return isRealDevice ? iosCaps.getRealIosMobileCaps(propertiesMap) : iosCaps.getSimulatorCaps(propertiesMap);
  }

}

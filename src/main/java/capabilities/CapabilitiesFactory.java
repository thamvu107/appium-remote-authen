package capabilities;

import enums.DeviceUnderTestType;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import utils.propertyReader.PropertiesMap;

import static capabilities.AndroidCapabilitiesManager.getEmulatorCaps;
import static capabilities.AndroidCapabilitiesManager.getRealMobileCaps;
import static capabilities.IOSCapabilitiesManager.getRealIosMobileCaps;
import static capabilities.IOSCapabilitiesManager.getSimulatorCaps;

public class CapabilitiesFactory {
//  private static PropertiesUtils deviceProps;

  private CapabilitiesFactory() {
  }

  //  public CapabilitiesFactory(String deviceCapConfig) {
//    deviceProps = new PropertiesUtils(deviceCapConfig);
//  }
//    public static BaseOptions<?> getCaps(PlatformType platformType, String deviceCapConfig) {
//
//    switch (platformType) {
//      case ANDROID:
//        return getAndroidCaps();
//      case IOS:
//        return getIOSCaps();
//      default:
//        throw new PlatformNotSupportException("Platform " + mobile.getPlatformName() + " is not supported");
//    }
//  }

  public static UiAutomator2Options getAndroidCaps(PropertiesMap deviceProps) {
//    PropertiesUtils deviceProps = new PropertiesUtils(deviceCapConfig);
//    boolean isEmulator = deviceProps.getProperty("deviceType").equals("emulator");

    switch (DeviceUnderTestType.valueOf(deviceProps.getProperty("deviceType"))) {
      case EMULATOR:
        return getEmulatorCaps(deviceProps);
      case PHYSICAL:
      case REAL:
        return getRealMobileCaps(deviceProps);
      default:
        throw new IllegalArgumentException("Unsupported Android mobile type");
    }
  }

  public static XCUITestOptions getIOSCaps(PropertiesMap deviceProps) {

    switch (DeviceUnderTestType.valueOf(deviceProps.getProperty("deviceType"))) {
      case EMULATOR:
        return getSimulatorCaps(deviceProps);
      case PHYSICAL:
      case REAL:
        return getRealIosMobileCaps(deviceProps);
      default:
        throw new IllegalArgumentException("Unsupported Android mobile type");
    }
  }

}

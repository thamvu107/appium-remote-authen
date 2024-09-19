package capabilities;


import enums.DeviceType;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.propertyReader.PropertiesMap;

import java.io.File;
import java.time.Duration;
import java.util.Objects;

import static java.time.Duration.ofSeconds;


public class AndroidCapabilitiesManager {
//  PropertiesUtils props = new PropertiesUtils("commonAndroidCaps.properties");

  public static UiAutomator2Options getEmulatorCaps(PropertiesMap deviceProps) {
//    PropertiesUtils deviceProps = new PropertiesUtils(deviceCapConfig);
    boolean isUseUdid = deviceProps.getBooleanProperty("isUseUdid");
    UiAutomator2Options caps = new UiAutomator2Options();
    try {
      if (isUseUdid) {
        caps.setSystemPort(deviceProps.getIntProperty("systemPort"))
          .setUdid(deviceProps.getProperty("udid"))
          .setAppPackage(deviceProps.getProperty("appPackage"))
          .setAppActivity(deviceProps.getProperty("appActivity"))
          .setAppWaitForLaunch(deviceProps.getBooleanProperty("appWaitForLaunch"))
          .setAppWaitDuration(ofSeconds(deviceProps.getLongProperty("appWaitDuration")))
          .setAutoGrantPermissions(deviceProps.getBooleanProperty("autoGrantPermissions"))
          .setRemoteAppsCacheLimit(deviceProps.getIntProperty("remoteAppsCacheLimit"))
          .setFullReset(deviceProps.getBooleanProperty("fullReset"))
          .setNoReset(deviceProps.getBooleanProperty("noReset"))
          .setMjpegServerPort(deviceProps.getIntProperty("mjpegServerPort"))
          .setMjpegScreenshotUrl(deviceProps.getProperty("mjpegScreenshotUrl"))
          .setPrintPageSourceOnFindFailure(deviceProps.getBooleanProperty("printPageSourceOnFindFailure"));
        caps.setCapability("clearSystemFiles", true);
        caps.setCapability("clearDeviceLogsOnStart", true);

      } else {
        caps.setSystemPort(deviceProps.getIntProperty("systemPort"))
          .setUiautomator2ServerLaunchTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerLaunchTimeout")))
          .setUiautomator2ServerInstallTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerInstallTimeout")))
          .setUiautomator2ServerReadTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerReadTimeout")))
          .setAllowDelayAdb(deviceProps.getBooleanProperty("allowDelayAdb"))
          .setAdbExecTimeout(ofSeconds(deviceProps.getIntProperty("adbExecTimeout")))
          .setDeviceName(deviceProps.getProperty("deviceName"))
          .setPlatformVersion(deviceProps.getProperty("platformVersion"))
          .setNewCommandTimeout(ofSeconds(deviceProps.getLongProperty("newCommandTimeout")))
          .setSuppressKillServer(true)
          .setAvd(deviceProps.getProperty("avd"))
          .setAvdLaunchTimeout(Duration.ofSeconds(deviceProps.getIntProperty("avdLaunchTimeout")))
          .setAvdReadyTimeout(Duration.ofSeconds(deviceProps.getIntProperty("avdReadyTimeout")))
          .setIsHeadless(deviceProps.getBooleanProperty("isHeadless"))
//        .setApp(Objects.requireNonNull(
//            AndroidCapabilitiesManager.class.getClassLoader().getResource("apps" + File.separator + deviceProps.getProperty("app")))
//                  .getPath())
//        .setApp(deviceProps.getProperty("app"))
//      .setEnforceAppInstall(true)
          .setAppPackage(deviceProps.getProperty("appPackage"))
          .setAppWaitPackage(deviceProps.getProperty("appWaitPackage"))
          .setAppActivity(deviceProps.getProperty("appActivity"))
          .setAppWaitActivity(deviceProps.getProperty("appWaitActivity"))
          .setAppWaitForLaunch(deviceProps.getBooleanProperty("appWaitForLaunch"))
          .setAppWaitDuration(ofSeconds(deviceProps.getLongProperty("appWaitDuration")))
          .setAutoGrantPermissions(deviceProps.getBooleanProperty("autoGrantPermissions"))
          .setRemoteAppsCacheLimit(deviceProps.getIntProperty("remoteAppsCacheLimit"))
          .setAndroidInstallTimeout(ofSeconds(deviceProps.getLongProperty("androidInstallTimeout")))
//      .setIgnoreHiddenApiPolicyError(true)
          .setFullReset(deviceProps.getBooleanProperty("fullReset"))
          .setNoReset(deviceProps.getBooleanProperty("noReset"));

        caps.setCapability("clearSystemFiles", true);
        caps.setCapability("clearDeviceLogsOnStart", true);
        caps.setCapability("enableWebviewDetailsCollection", true);


      }
    } catch (Exception e) {
      System.out.println("caps : " + caps);
      throw new RuntimeException(e);
    }

    return caps;
  }


  public static UiAutomator2Options getRealMobileCaps(PropertiesMap deviceProps) {
//    PropertiesUtils deviceProps = new PropertiesUtils(deviceCapConfig);

    // Capabilities
    try {
      UiAutomator2Options caps = new UiAutomator2Options()
        .setSystemPort(deviceProps.getIntProperty("systemPort"))
        .setUiautomator2ServerLaunchTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerLaunchTimeout")))
        .setUiautomator2ServerInstallTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerInstallTimeout")))
        .setUiautomator2ServerReadTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerReadTimeout")))
        .setAllowDelayAdb(deviceProps.getBooleanProperty("allowDelayAdb"))
        .setAdbExecTimeout(ofSeconds(deviceProps.getIntProperty("adbExecTimeout")))
        .setUdid(deviceProps.getProperty("udid"))
        //      .setDeviceName(deviceProps.getProperty("deviceName"))
        //      .setPlatformVersion(deviceProps.getProperty("platformVersion"))
        .setNewCommandTimeout(ofSeconds(deviceProps.getLongProperty("newCommandTimeout")))
        //      .setSuppressKillServer(true)
        .setApp(Objects.requireNonNull(
            AndroidCapabilitiesManager.class.getClassLoader().getResource("apps" + File.separator + deviceProps.getProperty("app")))
                  .getPath())
        //      .setEnforceAppInstall(true)
        .setAppPackage(deviceProps.getProperty("appPackage"))
        .setAppWaitPackage(deviceProps.getProperty("appWaitPackage"))
        .setAppActivity(deviceProps.getProperty("appActivity"))
        .setAppWaitActivity(deviceProps.getProperty("appWaitActivity"))
        .setAppWaitForLaunch(deviceProps.getBooleanProperty("appWaitForLaunch"))
        .setAppWaitDuration(ofSeconds(deviceProps.getLongProperty("appWaitDuration")))
        .setAutoGrantPermissions(deviceProps.getBooleanProperty("autoGrantPermissions"))
        .setRemoteAppsCacheLimit(deviceProps.getIntProperty("remoteAppsCacheLimit"))
        .setAndroidInstallTimeout(ofSeconds(deviceProps.getLongProperty("androidInstallTimeout")))
        .setIgnoreHiddenApiPolicyError(true)
        .setFullReset(deviceProps.getBooleanProperty("fullReset"))
        .setNoReset(deviceProps.getBooleanProperty("noReset"));

      caps.setCapability("clearSystemFiles", true);
      caps.setCapability("clearDeviceLogsOnStart", true);
      caps.setCapability("enableWebviewDetailsCollection", true);


      return caps;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static UiAutomator2Options getCaps(DeviceType deviceType, String configureFile) {
//    PropertiesMap propertiesMap = getPropertiesMap(configureFile);
    PropertiesMap propertiesMap = new PropertiesMap(configureFile);

    UiAutomator2Options caps = null;
    switch (deviceType) {
      case EMULATOR:
        caps = AndroidCapabilitiesManager.getEmulatorCaps(propertiesMap);
        break;
      case REAL:
        caps = AndroidCapabilitiesManager.getRealMobileCaps(propertiesMap);
        break;
      default:
        throw new IllegalStateException("Unexpected device type: " + deviceType);
    }
    if (caps == null) {
      throw new IllegalArgumentException("Capabilities is null");
    }

    return caps;
  }
}

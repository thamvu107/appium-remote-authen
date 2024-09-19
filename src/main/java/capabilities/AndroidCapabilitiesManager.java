package capabilities;


import io.appium.java_client.android.options.UiAutomator2Options;
import utils.propertyReader.PropertiesMap;

import java.io.File;
import java.time.Duration;
import java.util.Objects;

import static java.time.Duration.ofSeconds;


public class AndroidCapabilitiesManager {
  //  private DeviceType deviceType;
  private String configureFile;

  public AndroidCapabilitiesManager(String configureFile) {
    this.configureFile = configureFile;
  }

//  public UiAutomator2Options getCaps() {
//    PropertiesMap propertiesMap = new PropertiesMap(configureFile);
//
//    boolean isRealDevice = propertiesMap.getBooleanProperty("isRealDevice");
//
//    return isRealDevice ? getRealMobileCaps(propertiesMap) : getEmulatorCaps(propertiesMap);
//  }

  public UiAutomator2Options getEmulatorCaps(PropertiesMap deviceProps) {
    boolean isUseUdid = deviceProps.getBooleanProperty("isUseUdid");
    UiAutomator2Options caps = new UiAutomator2Options();
    caps = isUseUdid ? setCapsEmulatorIsRunning(caps, deviceProps) : setCapsEmulatorNotStarted(caps, deviceProps);

    try {
      caps.setAppPackage(deviceProps.getProperty("appPackage"))
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
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return caps;
  }

  public UiAutomator2Options getRealMobileCaps(PropertiesMap deviceProps) {

    UiAutomator2Options caps = new UiAutomator2Options();
    boolean isUseUdid = deviceProps.getBooleanProperty("isUseUdid");
    caps = isUseUdid ? setCapsRealDeviceUseUdid(caps, deviceProps) : setCapsRealDeviceUseDeviceNameAndPlatformVersion(caps, deviceProps);

    try {
      caps.setSystemPort(deviceProps.getIntProperty("systemPort"))
        .setUiautomator2ServerLaunchTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerLaunchTimeout")))
        .setUiautomator2ServerInstallTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerInstallTimeout")))
        .setUiautomator2ServerReadTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerReadTimeout")))
        .setAllowDelayAdb(deviceProps.getBooleanProperty("allowDelayAdb"))
        .setAdbExecTimeout(ofSeconds(deviceProps.getIntProperty("adbExecTimeout")))
        .setUdid(deviceProps.getProperty("udid"))
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

  private UiAutomator2Options setCapsEmulatorNotStarted(UiAutomator2Options caps, PropertiesMap deviceProps) {

    caps.setUiautomator2ServerLaunchTimeout(ofSeconds(deviceProps.getLongProperty("uiautomator2ServerLaunchTimeout")))
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
      .setIsHeadless(deviceProps.getBooleanProperty("isHeadless"));

    return caps;
  }

  private UiAutomator2Options setCapsEmulatorIsRunning(UiAutomator2Options caps, PropertiesMap deviceProps) {
    try {
      caps.setUdid(deviceProps.getProperty("udid"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return caps;
  }

  private UiAutomator2Options setCapsRealDeviceUseUdid(UiAutomator2Options caps, PropertiesMap deviceProps) {
    try {
      caps.setUdid(deviceProps.getProperty("udid"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return caps;

  }

  private UiAutomator2Options setCapsRealDeviceUseDeviceNameAndPlatformVersion(UiAutomator2Options caps, PropertiesMap deviceProps) {
    try {
      caps.setDeviceName(deviceProps.getProperty("deviceName"))
        .setPlatformVersion(deviceProps.getProperty("platformVersion"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return caps;
  }

}

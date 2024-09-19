package driverFactory.capabilities;

import constants.android.IAndroidDriverSetting;
import devices.android.AndroidPhysicalMobile;
import devices.android.Emulator;
import entity.app.AndroidAppUnderTest;
import io.appium.java_client.android.options.UiAutomator2Options;


public class AndroidCapabilities extends UiAutomator2Options implements IAndroidDriverSetting {

  static AndroidAppUnderTest app = new AndroidAppUnderTest();


  public static UiAutomator2Options getEmulatorCaps(Emulator mobile) {

//        AndroidAppUnderTest app = new AndroidAppUnderTest();

    UiAutomator2Options caps = new UiAutomator2Options()
      .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
      .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
      .setUiautomator2ServerReadTimeout(UIAUTOMATOR2_SERVER_READY_TIMEOUT)
      .setSkipServerInstallation(false)
      .setAllowDelayAdb(true)
      .setAdbExecTimeout(ADB_EXEC_TIMEOUT)
      .setDeviceName(mobile.getDeviceName())
      .setPlatformVersion(mobile.getPlatformVersion())
      .setNewCommandTimeout(NEW_COMMAND_TIMEOUT)
      .setSuppressKillServer(true)
//                .setUdid("emulator-5554")
      .setAvd(mobile.getAvd())
      .setAvdLaunchTimeout(mobile.getAvdLaunchTimeout())
      .setAvdReadyTimeout(mobile.getAvdReadyTimeout())

//                .setAvdEnv()
      .setApp(app.getAppPath())
      .setEnforceAppInstall(true)
      .setAppPackage(app.getAppPackage())
      .setAppWaitPackage(app.getAppPackage())
      .setAppActivity(app.getAppActivity())
      .setAppWaitActivity(app.getAppActivity())
      .setAppWaitForLaunch(app.isAppWaitForLaunch())
      .setAppWaitDuration(app.getAppWaitDuration())
      .setAutoGrantPermissions(app.isAutoGrantPermission())
      .setRemoteAppsCacheLimit(app.getRemoteAppCacheLimit())
      .setAndroidInstallTimeout(app.getAndroidInstallTimeout())

//                .setNetworkSpeed(String.valueOf(NetworkSpeed.LTE).toLowerCase()) // Original error: Cannot read properties of undefined (reading 'NETWORK_SPEED')
//                .setAvdArgs(mobile.getAvdArgs())
      .setIsHeadless(mobile.isHeadless())
      .setIgnoreHiddenApiPolicyError(true)
      .setFullReset(false)
      .setNoReset(false)
      .clearDeviceLogsOnStart();

    caps.setCapability("clearSystemFiles", true);
    caps.setCapability("clearDeviceLogsOnStart", true);
    caps.setCapability("enableWebviewDetailsCollection", true);

    caps.getAvdReadyTimeout();
    caps.getAdbExecTimeout();
    caps.getAvdLaunchTimeout();
    caps.getAndroidInstallTimeout();
    // not going to run your tests in parallel
//        caps.setCapability("--session-override", true);


    return caps;
  }


  public static UiAutomator2Options getRealMobileCaps(AndroidPhysicalMobile mobile) {

    // Capabilities
    UiAutomator2Options caps = new UiAutomator2Options()
      .setDeviceName(mobile.getDeviceName())
      .setUdid(mobile.getUdid())
      .setApp(app.getAppPath())
      .setAppPackage(app.getAppPackage())
      .setAppActivity(app.getAppActivity())
      .setAppWaitForLaunch(app.isAppWaitForLaunch())
      .setAppWaitDuration(app.getAppWaitDuration())
      .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
      .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
      .setFullReset(false)
      .setNoReset(false);

    caps.setCapability("clearDeviceLogsOnStart", true);

    return caps;
  }

}

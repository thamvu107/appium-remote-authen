package constants.android;

import utils.propertyReader.PropertiesMap;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public interface IAndroidDriverSetting {
  PropertiesMap props = new PropertiesMap("commandAndroidCaps.properties");
  Duration UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT = ofSeconds(props.getIntProperty("uiautomator2ServerLaunchTimeout")); // 30_000ms
  Duration UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT = ofSeconds(props.getIntProperty("uiautomator2ServerInstallTimeout")); // 20_000ms
  Duration UIAUTOMATOR2_SERVER_READY_TIMEOUT = ofSeconds(props.getIntProperty("uiautomator2ServerReadTimeout")); // 240_000ms

  Duration NEW_COMMAND_TIMEOUT = ofSeconds(props.getIntProperty("newCommandTimeout")); // 60 seconds
  Duration ADB_EXEC_TIMEOUT = ofSeconds(props.getIntProperty("adbExecTimeout")); // 20_000ms
  Duration ANDROID_INSTALL_TIMEOUT = ofSeconds(120); // 90_000ms
}

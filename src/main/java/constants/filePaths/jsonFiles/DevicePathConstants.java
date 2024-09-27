package constants.filePaths.jsonFiles;

import java.io.File;


public interface DevicePathConstants extends JsonFileConstants {
  String DEVICES_PATH = TEST_RESOURCES_PATH + "deviceCapConfig" + File.separator;
  String LOCAL_DEVICES_PATH = DEVICES_PATH + "devicesOnLocal" + File.separator;
  String REMOTE_DEVICES_PATH = DEVICES_PATH + "devicesOnRemote" + File.separator;
  String LOCAL_ANDROID_DEVICES_JSON_PATH = LOCAL_DEVICES_PATH + "android" + File.separator;
  String LOCAL_IOS_DEVICES_JSON_PATH = LOCAL_DEVICES_PATH + "ios" + File.separator;
  String REMOTE_ANDROID_DEVICES_JSON_PATH = REMOTE_DEVICES_PATH + "android" + File.separator;
  String REMOTE_IOS_DEVICES_JSON_PATH = REMOTE_DEVICES_PATH + "ios" + File.separator;

  String DEVICES_JSON_FILE= DEVICES_PATH + "devices.json";
  String ANDROID_DEVICES_FILE = DEVICES_PATH + "androidDevices.json";
  String IOS_DEVICES_FILE= DEVICES_PATH + "iosDevices.json";
  String LOCAL_ANDROID_DEVICES_FILE = LOCAL_ANDROID_DEVICES_JSON_PATH + "androidDevices.json";
  String LOCAL_IOS_DEVICES_FILE = LOCAL_IOS_DEVICES_JSON_PATH + "iosDevices.json";
  String REMOTE_ANDROID_DEVICES_FILE = REMOTE_ANDROID_DEVICES_JSON_PATH + "androidDevices.json";
  String REMOTE_IOS_DEVICES_FILE = REMOTE_IOS_DEVICES_JSON_PATH + "iosDevices.json";
}

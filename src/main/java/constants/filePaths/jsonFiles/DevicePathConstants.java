package constants.filePaths.jsonFiles;

import java.io.File;


public interface DevicePathConstants extends JsonFileConstants {
  String DEVICES_PATH = TEST_RESOURCES_PATH + "devices" + File.separator;
  String DEVICES_JSON_PATH = DEVICES_PATH + "devices.json";
  String ANDROID_DEVICES_JSON_PATH = DEVICES_PATH + "androidDevices.json";
  String IOS_DEVICES_JSON_PATH = DEVICES_PATH + "iosDevices.json";
}

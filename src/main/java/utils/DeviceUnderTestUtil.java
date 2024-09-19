package utils;

import constants.filePaths.jsonFiles.DevicePathConstants;
import entity.deviceConfigure.DeviceUnderTest;

import java.nio.file.Path;
import java.util.List;

public class DeviceUnderTestUtil {
  private DeviceUnderTest[] getDeviceConfigures() {
    Path deviceConfigDataPath = Path.of(DevicePathConstants.DEVICES_JSON_PATH);

    return DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceUnderTest[].class);
  }

  private List<DeviceUnderTest> getAndroidDevices() {
    Path deviceConfigDataPath = Path.of(DevicePathConstants.ANDROID_DEVICES_JSON_PATH);

    return DataObjectBuilderUtil.buildDataObjectList(deviceConfigDataPath, DeviceUnderTest.class);
  }

  private List<DeviceUnderTest> getIosDevices() {
    Path deviceConfigDataPath = Path.of(DevicePathConstants.ANDROID_DEVICES_JSON_PATH);

    return DataObjectBuilderUtil.buildDataObjectList(deviceConfigDataPath, DeviceUnderTest.class);
  }

}

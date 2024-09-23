package dataProvider.devices;

import constants.filePaths.jsonFiles.DevicePathConstants;
import entity.DeviceUnderTest;
import utils.DataObjectBuilderUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class IosDeviceUnderTestData {
  private IosDeviceUnderTestData() {
  }

  public  static List<DeviceUnderTest> readDeviceConfigsFromExternalSource() {

    Path deviceConfigDataPath = Paths.get(DevicePathConstants.IOS_DEVICES_JSON_PATH);
    DeviceUnderTest[] deviceArray = DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceUnderTest[].class);

    return Arrays.asList(deviceArray);
  }

}

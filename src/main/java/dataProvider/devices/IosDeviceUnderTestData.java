package dataProvider.devices;

import constants.filePaths.jsonFiles.DevicePathConstants;
import entity.DeviceUnderTest;
import lombok.extern.slf4j.Slf4j;
import utils.DataObjectBuilderUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class IosDeviceUnderTestData {
  private IosDeviceUnderTestData() {
  }


  public  static List<DeviceUnderTest> readLocalDeviceConfigsFromExternalSource() {

    Path deviceConfigDataPath = Paths.get(DevicePathConstants.LOCAL_IOS_DEVICES_FILE);
    DeviceUnderTest[] deviceArray = DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceUnderTest[].class);

    return Arrays.asList(deviceArray);
  }

  public  static List<DeviceUnderTest> readSeleniumGridDeviceConfigsFromExternalSource() {

    Path deviceConfigDataPath = Paths.get(DevicePathConstants.REMOTE_IOS_DEVICES_FILE);
    log.atInfo().log("deviceConfigDataPath = " + deviceConfigDataPath);

    DeviceUnderTest[] deviceArray = DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceUnderTest[].class);

    return Arrays.asList(deviceArray);
  }

}

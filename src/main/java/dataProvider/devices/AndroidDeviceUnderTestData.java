package dataProvider.devices;

import constants.filePaths.jsonFiles.DevicePathConstants;
import entity.DeviceUnderTest;
import org.testng.annotations.DataProvider;
import utils.DataObjectBuilderUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AndroidDeviceUnderTestData {
  private AndroidDeviceUnderTestData() {
  }


  public  static List<DeviceUnderTest> readLocalDeviceConfigsFromExternalSource() {

    Path deviceConfigDataPath = Paths.get(DevicePathConstants.LOCAL_ANDROID_DEVICES_FILE);
    DeviceUnderTest[] deviceArray = DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceUnderTest[].class);

    return Arrays.asList(deviceArray);
  }

  public  static List<DeviceUnderTest> readSeleniumGridDeviceConfigsFromExternalSource() {

    Path deviceConfigDataPath = Paths.get(DevicePathConstants.REMOTE_ANDROID_DEVICES_FILE);
    DeviceUnderTest[] deviceArray = DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceUnderTest[].class);

    return Arrays.asList(deviceArray);
  }

}

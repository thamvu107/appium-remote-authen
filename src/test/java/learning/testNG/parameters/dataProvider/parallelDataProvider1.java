package learning.testNG.parameters.dataProvider;

import constants.filePaths.jsonFiles.DevicePathConstants;
import entity.deviceConfigure.DeviceUnderTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataObjectBuilderUtil;

import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class parallelDataProvider1 {
//  public static void main(String[] args) {
//    Path deviceConfigDataPath = Path.of(DevicePathConstants.DEVICE_JSON_PATH);
//
//    DeviceConfigureCaps[] deviceConfigureCaps =
//      DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceConfigureCaps[].class);
//
//    for (DeviceConfigureCaps deviceConfigure : deviceConfigureCaps) {
//      if (deviceConfigure.getPlatformType().equals("android") && deviceConfigure.getDeviceType().equals("emulator")) {
//
//        System.out.println(deviceConfigure);
//      }
//    }
//  }


  @Test(dataProvider = "platformsDataProvider")
  public void testAppium1(DeviceUnderTest config) {
    Instant start = Instant.now();
//    System.out.println("\ntestAppium1");
    System.out.println("\ntestAppium1: \nTest start time: " + start + " ms");

    // Example test logic using Appium
    printParams(config);

    // Simulate test execution time
    try {
      Thread.sleep(2_000); // Simulate test execution
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Instant finish = Instant.now();
    System.out.println("testAppium1: Test finish time: " + finish + " ms");

    long timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println("testAppium1: Test execution time: " + timeElapsed + " ms");
    // Your Appium test logic here
  }

  @Test(dataProvider = "platformsDataProvider")
  public void testAppium2(DeviceUnderTest config) {
    Instant start = Instant.now();
//    System.out.println("\ntestAppium2");
//    System.out.println("Test start time: " + start + " ms");
    System.out.println("\ntestAppium2: \nTest start time: " + start + " ms");

    // Example test logic using Appium
    printParams(config);

    // Simulate test execution time
    try {
      Thread.sleep(2000); // Simulate test execution
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Instant finish = Instant.now();
    System.out.println("testAppium2: Test finish time: " + finish + " ms");

    long timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println("testAppium2: Test execution time: " + timeElapsed + " ms");
  }


  @Test(dataProvider = "platformsDataProvider")
//  public void testAppium3(DeviceConfig config) {
  public void testAppium3(DeviceUnderTest config) {
    Instant start = Instant.now();
//    System.out.println("\ntestAppium2");
//    System.out.println("Test start time: " + start + " ms");
    System.out.println("\ntestAppium3: \nTest start time: " + start + " ms");

    printParams(config);

    // Simulate test execution time
    try {
      Thread.sleep(2000); // Simulate test execution
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Instant finish = Instant.now();
    System.out.println("testAppium3: Test finish time: " + finish + " ms");

    long timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println("testAppium3: Test execution time: " + timeElapsed + " ms");
  }

  private static void printParams(DeviceUnderTest config) {
    // Example test logic using Appium
    System.out.println("Running Appium test on platform: " + config.getPlatformType());
    System.out.println("File: " + config.getConfigureFile());
  }

  @DataProvider(name = "platformsDataProvider", parallel = true)
//  public Object[][] platformsDataProvider() {
  public DeviceUnderTest[] platformsDataProvider() {
    // Simulate fetching platform configurations dynamically (e.g., from JSON or external source)
//    List<DeviceConfig> configs = readDeviceConfigsFromExternalSource();
    DeviceUnderTest[] configs = readDeviceConfigsFromExternalSource();
//    return filterByPlatform(configs);
//    return convertListToObjectArray(configs);
    return configs;
  }

  //  private List<DeviceConfig> readDeviceConfigsFromExternalSource() {
  private DeviceUnderTest[] readDeviceConfigsFromExternalSource() {
    // Example: Simulating reading from an external JSON file or database
    Path deviceConfigDataPath = Path.of(DevicePathConstants.DEVICES_JSON_PATH);
    DeviceUnderTest[] deviceConfigureCaps =
      DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceUnderTest[].class);

    return deviceConfigureCaps;
  }

  private Object[][] convertListToObjectArray(List<DeviceConfig> configs) {
    Object[][] dataArray = new Object[configs.size()][1];
    for (int i = 0; i < configs.size(); i++) {
      dataArray[i][0] = configs.get(i);
    }
    return dataArray;
  }

  private Object[][] filterByPlatform(List<DeviceConfig> configs) {
    // Fetch platformType dynamically from an external source or system property
    String platformType = System.getProperty("platformType", "android"); // Default to "android" if not provided

    // Filter data for the specified platformType
    List<Object[]> filteredData = new ArrayList<>();
    for (DeviceConfig config : configs) {
      if (config.getPlatform().equals(platformType)) {
        filteredData.add(new Object[] {config.getPlatform(), config.getDeviceName(), config.getApp()});
      }
    }
    return filteredData.toArray(new Object[0][]);
  }

  // Class representing device configuration
  private static class DeviceConfig {
    private final String platform;
    private final String deviceName;
    private final String app;

    public DeviceConfig(String platform, String deviceName, String app) {
      this.platform = platform;
      this.deviceName = deviceName;
      this.app = app;
    }

    public String getPlatform() {
      return platform;
    }

    public String getDeviceName() {
      return deviceName;
    }

    public String getApp() {
      return app;
    }
  }

}

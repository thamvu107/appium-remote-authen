package learning.testNG.parameters.dataProvider;

import com.google.common.reflect.ClassPath;
import constants.filePaths.jsonFiles.DevicePathConstants;
import entity.deviceConfigure.DeviceUnderTest;
import enums.PlatformType;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;
import utils.DataObjectBuilderUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.filePaths.IBasePaths.TEST_RESOURCES_PATH;

public class parallelDataProvider2 {
  public static void main(String[] args) {
    // Get all test classes
    List<Class<?>> testClasses;
    try {
      testClasses = getTestClasses();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    DeviceUnderTest[] configs = readDeviceConfigsFromExternalSource();


    // Get platform
//    String platformType = System.getProperty("platform");
//    String platformType = null;
    String platformType = "android";

    // Devices under test
    List<DeviceUnderTest> deviceList = getDeviceUnderTest(platformType, configs);

    // Assign devices to test classes
    Map<DeviceUnderTest, List<Class<?>>> deviceCapConfigureList = assignTestClassToDevices(testClasses, deviceList);

    //deviceCapConfigureList.forEach((k, v) -> System.out.println(k.getId() + " " + v.size()));

    // Build dynamic test suite
    TestNG testNG = new TestNG();
    XmlSuite suite = new XmlSuite();
    suite.setName("Dynamic Regression");
    suite.setPreserveOrder(false);
    suite.setParallel(ParallelMode.TESTS);

    List<XmlTest> allTests = new ArrayList<>();
    for (DeviceUnderTest deviceCapConfigure : deviceCapConfigureList.keySet()) {
      XmlTest test = new XmlTest(suite);
      test.setName(deviceCapConfigure.getId());
      List<XmlClass> xmlClasses = new ArrayList<>();
      List<Class<?>> testClassesForDevice = deviceCapConfigureList.get(deviceCapConfigure);
      for (Class<?> testClass : testClassesForDevice) {
        xmlClasses.add(new XmlClass(testClass.getName()));
      }
      test.setXmlClasses(xmlClasses);
      test.addParameter("id", deviceCapConfigure.getId());
      test.addParameter("platformType", deviceCapConfigure.getPlatformType());
      test.addParameter("deviceType", deviceCapConfigure.getDeviceType());
      test.addParameter("configureFile", deviceCapConfigure.getConfigureFile());
      allTests.add(test);
    }
    suite.setTests(allTests);
    suite.setParallel(ParallelMode.TESTS);
    suite.setThreadCount(10);

    String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH"));
    String folderPath = TEST_RESOURCES_PATH + "suites" + File.separator + date;
    String fileName = "testNG-" + time + ".xml";

    // Create the directory if it doesn't exist
    File directory = new File(folderPath);
    if (!directory.exists()) {
      directory.mkdirs();
    }

    // Write the XML suite to a file
    Path filePath = Paths.get(folderPath, fileName);
    try (FileWriter writer = new FileWriter(filePath.toFile())) {
      writer.write(suite.toXml());
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Add TestSuite into Suite list
    List<XmlSuite> suites = new ArrayList<>();
    suites.add(suite);

    // Invoke run method
    testNG.setXmlSuites(suites);
    testNG.run();
  }

  private static Map<DeviceUnderTest, List<Class<?>>> assignTestClassToDevices(List<Class<?>> testClasses,
                                                                               List<DeviceUnderTest> deviceList) {
    Map<DeviceUnderTest, List<Class<?>>> deviceCapConfigureList = new HashMap<>();
    int testNumEachDevice = testClasses.size() / deviceList.size();
    for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
      int startIndex = deviceIndex * testNumEachDevice;
      boolean isTheLastDevice = deviceIndex == deviceList.size() - 1;
      int endIndex = isTheLastDevice ? testClasses.size() : (startIndex + testNumEachDevice);
      List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
      deviceCapConfigureList.put(deviceList.get(deviceIndex), subTestList);
    }
    return deviceCapConfigureList;
  }


  private static List<DeviceUnderTest> getDeviceUnderTest(String platformType, DeviceUnderTest[] configs) {
    boolean isAllPlatformTypes = platformType == null;
    List<DeviceUnderTest> configsList = Arrays.asList(configs);
    List<DeviceUnderTest> iosDeviceList = filterByPlatform(configsList, PlatformType.IOS);
    List<DeviceUnderTest> androidDeviceList = filterByPlatform(configsList, PlatformType.ANDROID);

    List<DeviceUnderTest> deviceList = new ArrayList<>();

    if (isAllPlatformTypes) {
      deviceList.addAll(iosDeviceList);
      deviceList.addAll(androidDeviceList);

    } else if (PlatformType.fromString(platformType).equals(PlatformType.IOS)) {
      deviceList = iosDeviceList;
    } else if (PlatformType.fromString(platformType).equals(PlatformType.ANDROID)) {
      deviceList = androidDeviceList;
    }

    return deviceList;
  }

  private static List<Class<?>> getTestClasses() throws IOException {
    final ClassLoader loader = Thread.currentThread().getContextClassLoader();
    List<Class<?>> testClasses = new ArrayList<>();
    for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
      String classInfoName = info.getName();
      boolean startWithTestDot = classInfoName.startsWith("learning.testNG.parallel.parallelDataProvider.testCases.");
      if (startWithTestDot) {
        testClasses.add(info.load());
      }
    }

    System.out.println("Total test classes: " + testClasses.size());
    return testClasses;
  }

  private static void printParams(DeviceUnderTest config) {
    // Example test logic using Appium
    System.out.println("\nRunning Appium test on platform: " + config.getPlatformType());
    System.out.println("Device Id: " + config.getId());
    System.out.println("Device Type: " + config.getDeviceType());
    System.out.println("File: " + config.getConfigureFile());
  }

  @DataProvider(name = "platformsDataProvider", parallel = true)
  public DeviceUnderTest[] platformsDataProvider() {
    return readDeviceConfigsFromExternalSource();
  }

  private static DeviceUnderTest[] readDeviceConfigsFromExternalSource() {

    Path deviceConfigDataPath = Path.of(DevicePathConstants.DEVICES_JSON_PATH);

    return DataObjectBuilderUtil.buildDataObject(deviceConfigDataPath, DeviceUnderTest[].class);
  }


  private static List<DeviceUnderTest> filterByPlatform(List<DeviceUnderTest> configs, PlatformType platformType) {
    // Filter data for the specified platformType
    List<DeviceUnderTest> filteredData = new ArrayList<>();
    for (DeviceUnderTest config : configs) {
      if (PlatformType.fromString(config.getPlatformType()).equals(platformType)) {

        filteredData.add(config);
      }
    }
    return filteredData;
  }
}

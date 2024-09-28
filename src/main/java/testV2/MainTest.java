package testV2;

import com.google.common.reflect.ClassPath;
import config.factory.GeneralConfigFactory;
import constants.filePaths.jsonFiles.DevicePathConstants;
import dataProvider.devices.AndroidDeviceUnderTestData;
import dataProvider.devices.IosDeviceUnderTestData;
import entity.DeviceUnderTest;
import enums.MobileRunModeType;
import enums.PlatformType;
import lombok.extern.slf4j.Slf4j;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.filePaths.IBasePaths.TEST_RESOURCES_PATH;

@Slf4j
public class MainTest {
  public static void main(String[] args) throws IOException {
    // Get all test classes
    List<Class<?>> testClasses = getTestClasses();

    // Get Platform
    PlatformType platformType = getPlatformType();

    MobileRunModeType mobileRunModeType = getMobileRunMode();


    // Devices under test
    List<DeviceUnderTest> deviceList = getDeviceUnderTests(mobileRunModeType, platformType);
    String deviceUnderTestPath = getDeviceUnderTestPath(mobileRunModeType, platformType);

    // Assign devices to test classes
    Map<DeviceUnderTest, List<Class<?>>> desiredCap = assignTestClassesToDevices(testClasses, deviceList);

    // Build dynamic test suite
    TestNG testNG = new TestNG();
    XmlSuite suite = new XmlSuite();
    suite.setName("Dynamic Test Suite");

    Map<String, String> suiteParameters = new HashMap<>();
    suiteParameters.put("platformType", platformType.getPlatformName().toUpperCase());
    suite.setParameters(suiteParameters);

    // Add listener
    suite.addListener("listeners.TestListener");


    List<XmlTest> allTest = new ArrayList<>();
    for (DeviceUnderTest deviceUnderTest : desiredCap.keySet()) {
      XmlTest test = new XmlTest(suite);
      test.setName(deviceUnderTest.getDeviceName() + " - " + deviceUnderTest.getPlatformVersion());
      List<XmlClass> xmlClasses = new ArrayList<>();
      List<Class<?>> testClassesForDevice = desiredCap.get(deviceUnderTest);
      for (Class<?> testClass : testClassesForDevice) {
        xmlClasses.add(new XmlClass(testClass.getName()));
      }
      test.setXmlClasses(xmlClasses);
      String configureFilePath = deviceUnderTestPath + deviceUnderTest.getConfigureFile();
      test.addParameter("configureFile", configureFilePath);
      allTest.add(test);
    }

    suite.setTests(allTest);
    suite.setParallel(XmlSuite.ParallelMode.TESTS);
    suite.setThreadCount(deviceList.size());

    log.atInfo().log("suite.toXml() " + suite.toXml());
//    System.out.println("suite.toXml() " + suite.toXml());
    writeXmlFile(platformType, suite);

    // Add TestSuite into Suite list
    List<XmlSuite> suites = new ArrayList<>();
    suites.add(suite);

    // Invoke run method
    testNG.setXmlSuites(Collections.singletonList(suite));
    testNG.run();
  }

  private static String getDeviceUnderTestPath(MobileRunModeType mobileRunModeType, PlatformType platformType) {
    String fullPath;
    switch (mobileRunModeType) {
      case LOCAL:
        fullPath = getLocalDeviceUnderTestPath(platformType);
        break;
      case SELENIUM_GRID:
        fullPath = getSeleniumGridDeviceUnderTestPath(platformType);
        break;
      default:
        throw new RuntimeException("Invalid mobileRunModeType: " + mobileRunModeType);
    }

    String basePath = TEST_RESOURCES_PATH;
    return fullPath.substring(fullPath.indexOf(basePath) + basePath.length());
  }

  private static void writeXmlFile(PlatformType platformType, XmlSuite suite) {

    String folderPath = TEST_RESOURCES_PATH + "suites";
    String fileName = "testNG-" + platformType.getPlatformName() + ".xml";

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
  }

  private static Map<DeviceUnderTest, List<Class<?>>> assignTestClassesToDevices(List<Class<?>> testClasses,
                                                                                 List<DeviceUnderTest> deviceList) {
    int testNumEachDevice = testClasses.size() / deviceList.size();
    System.out.println("testNumEachDevice: " + testNumEachDevice);

    Map<DeviceUnderTest, List<Class<?>>> map = new HashMap<>();
    for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
      int startIndex = deviceIndex * testNumEachDevice;
      boolean isLastDevice = deviceIndex == deviceList.size() - 1;
      int endIndex = isLastDevice ? testClasses.size() : (startIndex + testNumEachDevice);
      List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
      map.put(deviceList.get(deviceIndex), subTestList);
    }
    return map;
  }

  private static String getSeleniumGridDeviceUnderTestPath(PlatformType platformType) {
    String fullPath;
    switch (platformType) {
      case IOS:
        fullPath = DevicePathConstants.REMOTE_IOS_DEVICES_JSON_PATH;
        break;
      case ANDROID:
        fullPath = DevicePathConstants.REMOTE_ANDROID_DEVICES_JSON_PATH;
        break;
      default:
        log.atError().log("Invalid platform type: " + platformType);
        throw new RuntimeException("Invalid platform type: " + platformType);
    }

    log.atInfo().log("Selenium grid device under test path: " + fullPath);

    return fullPath;
  }

  private static String getLocalDeviceUnderTestPath(PlatformType platformType) {
    String fullPath;
    switch (platformType) {
      case IOS:
        fullPath = DevicePathConstants.LOCAL_IOS_DEVICES_JSON_PATH;
        break;
      case ANDROID:
        fullPath = DevicePathConstants.LOCAL_ANDROID_DEVICES_JSON_PATH;
        break;
      default:
        log.atError().log("Invalid platform type: " + platformType);
        throw new RuntimeException("Invalid platform type: " + platformType);
    }

    log.atInfo().log("Selenium grid device under test path: " + fullPath);

    return fullPath;
  }


  private static List<DeviceUnderTest> getDeviceUnderTests(MobileRunModeType mobileRunModeType, PlatformType platformType) {

    List<DeviceUnderTest> deviceList;
    switch (mobileRunModeType) {
      case LOCAL:
        deviceList = getLocalDeviceUnderTests(platformType);
        break;
      case SELENIUM_GRID:
        deviceList = getSeleniumGridDeviceUnderTests(platformType);
        break;
      default:
        log.atError().log("Invalid mobile run mode type: " + mobileRunModeType);
        throw new RuntimeException("Invalid mobile run mode type: " + mobileRunModeType);
    }

    log.atInfo().log("Total number of device under test: " + deviceList.size() + "\n" + deviceList.toString());


    return deviceList;
  }

  private static List<DeviceUnderTest> getLocalDeviceUnderTests(PlatformType platformType) {

    List<DeviceUnderTest> iosDevices = IosDeviceUnderTestData.readLocalDeviceConfigsFromExternalSource();
    List<DeviceUnderTest> androidDevices = AndroidDeviceUnderTestData.readLocalDeviceConfigsFromExternalSource();

    return platformType == PlatformType.IOS ? iosDevices : androidDevices;
  }


  private static List<DeviceUnderTest> getSeleniumGridDeviceUnderTests(PlatformType platformType) {

    List<DeviceUnderTest> iosDevices = IosDeviceUnderTestData.readSeleniumGridDeviceConfigsFromExternalSource();
    List<DeviceUnderTest> androidDevices = AndroidDeviceUnderTestData.readSeleniumGridDeviceConfigsFromExternalSource();

    return platformType == PlatformType.IOS ? iosDevices : androidDevices;
  }

  private static PlatformType getPlatformType() {
    PlatformType platformType = GeneralConfigFactory.getConfig().getPlatformType();
    log.atInfo().log("Running test on platform: " + platformType);

    return platformType;
  }

  private static MobileRunModeType getMobileRunMode() {
    MobileRunModeType mobileRunModeType = GeneralConfigFactory.getConfig().getMobileRunMode();
    log.atInfo().log("Running test on mobile run mode: " + mobileRunModeType);

    return mobileRunModeType;
  }

  private static List<Class<?>> getTestClasses() throws IOException {
    final ClassLoader loader = Thread.currentThread().getContextClassLoader();
    List<Class<?>> testClasses = new ArrayList<>();
    for (ClassPath.ClassInfo info : ClassPath.from(loader).getAllClasses()) {
      String classInfoName = info.getName();
      boolean startWithTestDot = classInfoName.startsWith("testV2.");
      boolean isMainTestClass = classInfoName.startsWith("testV2.MainTest");
      if (startWithTestDot && !isMainTestClass) {
        log.atInfo().log("Found test class: " + classInfoName);
        testClasses.add(info.load());
      }
    }

//    System.out.println("Total test classes: " + testClasses.size());

    return testClasses;
  }

}

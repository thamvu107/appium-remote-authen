package testV2;

import com.google.common.reflect.ClassPath;
import config.factory.GeneralConfigFactory;
import dataProvider.devices.AndroidDeviceUnderTestData;
import dataProvider.devices.IosDeviceUnderTestData;
import entity.DeviceUnderTest;
import enums.PlatformType;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.filePaths.IBasePaths.TEST_RESOURCES_PATH;

public class MainTestV1 {
  public static void main(String[] args) throws IOException {
    // Get all test classes
    List<Class<?>> testClasses = getTestClasses();

    // Get Platform
    PlatformType platformType = getPlatformType();

    // Devices under test
    List<DeviceUnderTest> deviceList = getDeviceUnderTests(platformType);

    // Assign devices to test classes
    Map<DeviceUnderTest, List<Class<?>>> desiredCap = assignTestClassesToDevices(testClasses, deviceList);

    // Build dynamic test suite
    TestNG testNG = new TestNG();
    XmlSuite suite = new XmlSuite();
    suite.setName("Dynamic Test Suite");
    Map<String, String> suiteParameters = new HashMap<>();
    suiteParameters.put("platformType", platformType.getPlatformName());
    suite.setParameters(suiteParameters);


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
      test.addParameter("configureFile", deviceUnderTest.getConfigureFile());
      allTest.add(test);
    }

    suite.setTests(allTest);
    suite.setParallel(XmlSuite.ParallelMode.TESTS);
    suite.setThreadCount(6);


    System.out.println("suite.toXml() " + suite.toXml());

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

  private static Map<DeviceUnderTest, List<Class<?>>> assignTestClassesToDevices(List<Class<?>> testClasses,
                                                                                 List<DeviceUnderTest> deviceList) {
    int testNumEachDevice = testClasses.size() / deviceList.size();
    System.out.println("testNumEachDevice: " +testNumEachDevice);

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

  private static List<DeviceUnderTest> getDeviceUnderTests(PlatformType platformType) {
    List<DeviceUnderTest> iosDevices = IosDeviceUnderTestData.readDeviceConfigsFromExternalSource();
    List<DeviceUnderTest> androidDevices = AndroidDeviceUnderTestData.readDeviceConfigsFromExternalSource();
    List<DeviceUnderTest> deviceList = platformType == PlatformType.IOS ? iosDevices : androidDevices;
    return deviceList;
  }

  private static PlatformType getPlatformType() {
    PlatformType platformType = GeneralConfigFactory.getConfig().getPlatformType();
    if (platformType == null) {
      throw new RuntimeException("Platform type not found");
    }
    return platformType;
  }

  private static List<Class<?>> getTestClasses() throws IOException {
    final ClassLoader loader = Thread.currentThread().getContextClassLoader();
    List<Class<?>> testClasses = new ArrayList<>();
    for (ClassPath.ClassInfo info : ClassPath.from(loader).getAllClasses()) {
      String classInfoName = info.getName();
//      System.out.println("classInfoName " + classInfoName);
      boolean startWithTestDot = classInfoName.startsWith("testV2.");
      boolean isMainTestClass = classInfoName.startsWith("testV2.MainTest");
      if (startWithTestDot && !isMainTestClass) {
        testClasses.add(info.load());
      }
    }

//    System.out.println("Total test classes: " + testClasses.size());

    return testClasses;
  }

}

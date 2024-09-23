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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {
  public static void main(String[] args) throws IOException {
    // Get all test classes
    final ClassLoader loader = Thread.currentThread().getContextClassLoader();
    List<Class<?>> testClasses = new ArrayList<>();
    for (ClassPath.ClassInfo info : ClassPath.from(loader).getAllClasses()) {
      String classInfoName = info.getName();
      boolean startsWithTestDot = classInfoName.startsWith("testV2.");
      boolean isMainTestClass = classInfoName.startsWith("testV2.MainTest");
      if (startsWithTestDot && !isMainTestClass) {
        testClasses.add(info.load());
      }
    }

    // Get Platform
    PlatformType platformType = GeneralConfigFactory.getConfig().getPlatformType();
    if (platformType == null) {
      throw new RuntimeException("Platform type not found");
    }


    // Devices under test
    List<DeviceUnderTest> iosDevices = IosDeviceUnderTestData.readDeviceConfigsFromExternalSource();
    List<DeviceUnderTest> androidDevices = AndroidDeviceUnderTestData.readDeviceConfigsFromExternalSource();
    List<DeviceUnderTest> deviceList = platformType == PlatformType.IOS ? iosDevices : androidDevices;

    // Assign devices to test classes
    int testNumEachDevice = testClasses.size() / deviceList.size();
    Map<DeviceUnderTest, List<Class<?>>> desiredCap = new HashMap<>();
    for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
      int startIndex = deviceIndex * testNumEachDevice;
      boolean isLastDevice = deviceIndex == deviceList.size() - 1;
      int endIndex = isLastDevice ? testClasses.size() : (startIndex + testNumEachDevice);
      List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
      desiredCap.put(deviceList.get(deviceIndex), subTestList);
    }

    // Build dynamic test suite
    TestNG testNG = new TestNG();
    XmlSuite suite = new XmlSuite();
    suite.setName("TestV2");

    List<XmlTest> allTest = new ArrayList<>();
    for (DeviceUnderTest deviceUnderTest : desiredCap.keySet()) {
      XmlTest test = new XmlTest(suite);
      test.setName(deviceUnderTest.getDeviceName() + " " + deviceUnderTest.getPlatformVersion());
      List<XmlClass> xmlClasses = new ArrayList<>();
      List<Class<?>> testClassesForDevice = desiredCap.get(deviceUnderTest);
      for (Class<?> testClass : testClassesForDevice) {
        xmlClasses.add(new XmlClass(testClass.getName()));
      }
      test.setXmlClasses(xmlClasses);
      test.addParameter("platformType", platformType.getPlatformName());
      test.addParameter("configureFile", deviceUnderTest.getConfigureFile());
      allTest.add(test);
    }

    suite.setTests(allTest);
    suite.setParallel(XmlSuite.ParallelMode.TESTS);
    suite.setThreadCount(6);

    System.out.println("suite.toXml() " + suite.toXml());
    // Add TestSuite into Suite list
    List<XmlSuite> suites = new ArrayList<>();
    suites.add(suite);

    // Invoke run method
    testNG.setXmlSuites(suites);
    testNG.run();
  }
}

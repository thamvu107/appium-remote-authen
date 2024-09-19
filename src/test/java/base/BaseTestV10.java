package base;

import driver.ThreadSafeDriver;
import enums.DeviceType;
import enums.PlatformType;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import testFlows.SignInFlow;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

@Slf4j
public abstract class BaseTestV10 {

  protected String getDeviceUdid() {
    return ThreadSafeDriver.getDriver().getCapabilities().getCapability("udid").toString();
  }

  @BeforeSuite
  public void beforeSuite() {

    MDC.put("logDir", "logs");
  }

  @BeforeTest
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void beforeTest(String platformType, String deviceType, String configureFile, ITestContext context) {

    ThreadSafeDriver.getDriver(PlatformType.valueOf(platformType), DeviceType.valueOf(deviceType), configureFile);

    context.getCurrentXmlTest().setName(
      context.getCurrentXmlTest().getName().concat(" - ").concat(platformType).concat(" - ").concat(deviceType).concat(" - ")
        .concat(getDeviceUdid()));
  }

  @BeforeMethod(alwaysRun = true)
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void setUp(String platformType, String deviceType, String configureFile) {

    ThreadSafeDriver.getDriver(PlatformType.valueOf(platformType), DeviceType.valueOf(deviceType), configureFile);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult results) {
    MDC.clear(); // Mapped Diagnostic Context
//    customizeParametersForAllureReport();

    ThreadSafeDriver.closeDriver();
  }

  protected void setLogParams(String platformType, String deviceType, String configureFile) {
    MDC.put("baseTest PlatformType:: ", platformType);
    MDC.put("baseTest PlatformType:: ", deviceType);
    MDC.put("baseTest PlatformType:: ", configureFile);

  }

  private static String convertDeviceName(String deviceName) {
    System.out.println(deviceName);
    if (deviceName == null || deviceName.isEmpty()) {
      throw new IllegalArgumentException("Device name cannot be null or empty");
    }
    return deviceName.replaceAll(" ", "_");
  }

  protected SignInFlow signInFlow() {
    return new SignInFlow(ThreadSafeDriver.getDriver());
  }

  protected void hideParametersForTestCasesInAllureReport() {
    AllureLifecycle lifecycle = Allure.getLifecycle();

    // Update the test case in Allure
    lifecycle.updateTestCase(testResult -> {

      for (Parameter parameter : testResult.getParameters()) {
        parameter.setMode(HIDDEN);
      }
    });
  }
}

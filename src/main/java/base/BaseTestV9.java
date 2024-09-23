package base;

import driver.ThreadSafeDriver;
import enums.PlatformType;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import testFlows.SignInFlow;

import static io.qameta.allure.model.Parameter.Mode.DEFAULT;
import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

@Slf4j
public abstract class BaseTestV9 {

  protected String getDeviceUdid() {
    return ThreadSafeDriver.getDriver().getCapabilities().getCapability("udid").toString();
  }

  @BeforeSuite
  public void beforeSuite() {

    MDC.put("logDir", "logs");
  }

  @BeforeMethod(alwaysRun = true)
  @Parameters({"platformType",  "configureFile"})
  public void setUp(String platformType,  String configureFile) {

    ThreadSafeDriver.getDriver(PlatformType.valueOf(platformType), configureFile);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult results) {

    ThreadSafeDriver.closeDriver();
  }

  protected void setLogParams(String platformType,  String configureFile) {
    MDC.put("baseTest PlatformType: ", platformType);
    MDC.put("baseTest configureFile:: ", configureFile);

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

  protected void customizeParametersForAllureReport() {
    AllureLifecycle lifecycle = Allure.getLifecycle();

    // Update the test case in Allure
    lifecycle.updateTestCase(testResult -> {

      for (Parameter parameter : testResult.getParameters()) {
        if (parameter.getName().equals("platformType") ) {
          parameter.setMode(DEFAULT);
        } else {
          parameter.setMode(HIDDEN);
        }
      }

      // Add the device Udid parameter
      String deviceUdid = getDeviceUdid();
      if (deviceUdid != null && !deviceUdid.isEmpty()) {
        Allure.parameter("Udid", deviceUdid);
      }
    });
  }
}

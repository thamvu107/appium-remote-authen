package base;

import driverFactory.DriverProvider;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.slf4j.MDC;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import screens.HomeScreen;

@Slf4j
public abstract class BaseTest {

  protected AppiumDriver driver;
  protected DriverProvider driverProvider;
  protected HomeScreen homeScreen;
  protected PlatformType currentPlatform;

  @BeforeSuite
  public void beforeTest() {
    //LaunchingEmulatorUtil.launchEmulator("Pixel_5_API_33_1");
//        LaunchingEmulatorUtil.launchEmulator("Pixel_4_API_33");
  }


  @AfterClass(alwaysRun = true)
  public void afterClass() {
    MDC.clear(); // Mapped Diagnostic Context
    driverProvider.quitDriver(driver);
  }

  @AfterSuite
  public void afterSuite() {

//        LaunchingEmulatorUtil.killEmulator();

  }

  protected void setLogParams(Capabilities capabilities) {
    String deviceName = capabilities.getCapability("deviceName").toString();
    MDC.put("logDir", "logs");
    MDC.put("deviceName", convertDeviceName(deviceName));
  }

  private static String convertDeviceName(String deviceName) {
    System.out.println(deviceName);
    if (deviceName == null || deviceName.isEmpty()) {
      throw new IllegalArgumentException("Device name cannot be null or empty");
    }
    return deviceName.replaceAll(" ", "_");
  }

}

package learning;

import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import screens.HomeScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import utils.ElementUtils;

import static devices.MobileFactory.getEmulator;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class SwipeUpToElement {
  public static void main(String[] args) {
    AppiumDriver driver = null;
    try {

      DriverProvider driverProvider = new DriverProvider();
      Capabilities caps = CapabilityFactory.getCaps(getEmulator());
      driver = driverProvider.getLocalServerDriver(caps);
      HomeScreen homeScreen = new HomeScreen(driver);
      homeScreen.verifyAppLaunched();
      homeScreen.goToSwipeScreen();

      ElementUtils elementUtils = new ElementUtils(driver);
      By targetLoc = accessibilityId("WebdriverIO logo");


//            scrollToTop(driver);
      swipeToAGivenTextAndClick(driver, "You found me!!!");

      elementUtils.waitForElementTobeClickable(targetLoc, 15000).click();


    } catch (Exception e) {
      throw new RuntimeException("Setup failed: " + e.getMessage(), e);
    } finally {

      driver.quit();
    }


  }

  public static void scrollToTop(AppiumDriver driver) {
    String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).flingToBeginning(3)";
    driver.findElement(AppiumBy.androidUIAutomator(command));
  }

  public static void swipeToAGivenTextAndClick(AppiumDriver driver, String elementText) {
    String uiSelector = "new UiSelector().textContains(\"" + elementText
      + "\")";
    String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("
      + uiSelector + ");";
    driver.findElement(AppiumBy.androidUIAutomator(command)).click();
  }

}

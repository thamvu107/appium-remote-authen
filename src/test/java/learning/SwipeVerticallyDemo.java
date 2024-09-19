package learning;

import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import utils.ElementUtils;
import utils.gestures.swipe.vertical.SwipeVertically;

import java.util.Map;

import static devices.MobileFactory.getEmulator;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;

public class SwipeVerticallyDemo {

  public static void main(String[] args) {

    AppiumDriver driver;

    DriverProvider driverProvider = new DriverProvider();
    Capabilities caps = CapabilityFactory.getCaps(getEmulator());
    driver = driverProvider.getLocalServerDriver(caps);

    try {
      By formsBtnLoc = accessibilityId("Forms");
      By activeBtnLoc = accessibilityId("button-Active");
      By androidFormComponent = androidUIAutomator("new UiSelector(). textContains(\"Form components\")");
      By iosFormComponent =
        iOSNsPredicateString("name == \"Form components\" AND label == \"Form components\" AND value == \"Form components\"");

      Map<PlatformType, By> formComponentLocatorMap =
        Map.of(PlatformType.ANDROID, androidFormComponent, PlatformType.IOS, iosFormComponent);

      // Navigate to [Forms] screen
      driver.findElement(formsBtnLoc).click();

      // Make sure we are on the target screen before swiping up/down/left/right/any direction
      ElementUtils elementUtils = new ElementUtils(driver);
      By formComponentLoc = elementUtils.getLocator(formComponentLocatorMap);
      new ElementUtils(driver).waitForFindingElement(formComponentLoc);

      SwipeVertically swipeVertically = new SwipeVertically(driver);

      swipeVertically.swipeUp();

      // Interact with element on the screen
      driver.findElement(activeBtnLoc).click();

    } catch (Exception e) {
      e.printStackTrace();
    }
    // DEBUG PURPOSE ONLY
    try {
      Thread.sleep(3000);
    } catch (Exception ignored) {
    }

    driver.quit();
  }


}

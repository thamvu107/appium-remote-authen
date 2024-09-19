package learning.componentV3.screen;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;

@Slf4j
public class HomeScreenV3 extends BaseScreenV3 {

  private final By homeScreenLoc = AppiumBy.accessibilityId("Home-screen");

  public HomeScreenV3(final AppiumDriver driver) {
    super(driver);
    log.atInfo().setMessage("Loading home screen").log();
    verifyScreenLoaded(homeScreenLoc);
  }

  private WebElement homeScreenEl() {
    return elementUtils.waitForElementToBeVisible(homeScreenLoc, LONG_EXPLICIT_WAIT);
  }

  private WebElement homeScreenEl(long durationInMillis) {
    return elementUtils.waitForElementToBeVisible(homeScreenLoc, durationInMillis);
  }

  public HomeScreenV3 verifyHomeScreenDisplayed() {
    elementUtils.isElementDisplayed(homeScreenEl());

    return this;
  }

  public boolean homeScreenDisplayed() {
    return elementUtils.isElementDisplayed(homeScreenEl());
  }

  public boolean verifyAppLaunched() {

    return elementUtils.isElementDisplayed(homeScreenEl());
  }

}

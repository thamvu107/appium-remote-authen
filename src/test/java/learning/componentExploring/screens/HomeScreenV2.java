package learning.componentExploring.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;

@Slf4j
public class HomeScreenV2 extends BaseScreenV2 {

  private final By homeScreenLoc = AppiumBy.accessibilityId("Home-screen");

  public HomeScreenV2(final AppiumDriver driver) {
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

  public HomeScreenV2 verifyHomeScreenDisplayed() {
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

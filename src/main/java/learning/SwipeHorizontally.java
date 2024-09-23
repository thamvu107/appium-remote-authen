package learning;

import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import enums.SwipeHorizontalDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import screens.HomeScreen;
import screens.SwipeScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import utils.ElementUtils;

import static constants.SwipeScreenConstants.SWIPE_SCREEN_TITLE;
import static devices.MobileFactory.getEmulator;

public class SwipeHorizontally {
  public static AppiumDriver driver;
  public static ElementUtils elementUtils;
  private static final By carouselContainerLoc = AppiumBy.accessibilityId("Carousel");
  private static final By currentCardLoc = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]");
  private static final By firstCardLoc =
    AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\"__CAROUSEL_ITEM_0_READY__\")");
  private static final By lastCardLoc = AppiumBy.accessibilityId("card");
  private static final By targetCardTitleLoc =
    AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer'][1]//android.widget.TextView[1]");

  public static void main(String[] args) {


    DriverProvider driverProvider = new DriverProvider();
    Capabilities caps = CapabilityFactory.getCaps(getEmulator());
    driver = driverProvider.getLocalServerDriver(caps);
    elementUtils = new ElementUtils(driver);
    try {
      HomeScreen homeScreen = new HomeScreen(driver);
      homeScreen.verifyAppLaunched();

      SwipeScreen swipeScreen = homeScreen.goToSwipeScreen();
      swipeScreen.goToSwipeScreen().verifySwipeScreenDisplayed().verifySwipeScreenTitle(SWIPE_SCREEN_TITLE).verifyCarouselDisplayed();
      swipeScreen.swipeLeftToCardTitle("SUPPORT VIDEOS", 4);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      driver.quit();
    }

  }

  private static void swipeToHorizontalDirection(utils.gestures.swipe.horizontal.SwipeHorizontally swipeHorizontal,
                                                 SwipeHorizontalDirection direction) {
    if (direction == SwipeHorizontalDirection.LEFT) {
      swipeHorizontal.swipeLeft();
    } else {
      swipeHorizontal.swipeRight();
    }
  }

  private static WebElement carouselContainerEl() {

    return elementUtils.waitForElementToBeVisible(carouselContainerLoc);
  }

  private static WebElement getCardTitleElement(WebElement currentCardEl) {
    return currentCardEl.findElement(targetCardTitleLoc);
  }


  private static boolean swipeToTargetCard(SwipeHorizontalDirection direction, By targetElementLoc, int maxSwipes) {

    utils.gestures.swipe.horizontal.SwipeHorizontally swipeHorizontal =
      new utils.gestures.swipe.horizontal.SwipeHorizontally(driver, carouselContainerEl());
    boolean isTargetFound = false;

    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      swipeToHorizontalDirection(swipeHorizontal, direction);
      if (elementUtils.isElementPresent(targetElementLoc)) {
        isTargetFound = true;
        break;
      }
    }
    return isTargetFound;
  }

}

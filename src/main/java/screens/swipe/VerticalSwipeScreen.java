package screens.swipe;

import base.BaseScreen;
import enums.SwipeVerticalDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import utils.gestures.swipe.vertical.SwipeVertically;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;

public class VerticalSwipeScreen extends BaseScreen {

  private final SwipeVertically swipeVertically;

  public VerticalSwipeScreen(AppiumDriver driver) {

    super(driver);
    verifyScreenLoaded(swipeScreenLoc);

    swipeVertically = initScrollDown(driver, swipeScreenEl(), carouselContainerEl());
  }


  private final By swipeScreenLoc = AppiumBy.accessibilityId("Swipe-screen");
  private final By carouselContainerLoc = AppiumBy.accessibilityId("Carousel");
  private final By swipeScreenTitleLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Swipe horizontal\")");

  //    private final By currentCardLoc = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]");

  private final By firstCardWrapperLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"__CAROUSEL_ITEM_0_READY__\")");

  //private final By firstCardLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"FULLY OPEN SOURCE\")");
  //NOTE: The requested id selector does not have a package name prefix.
  //private final By firstCardWrapperLoc = AppiumBy.id("__CAROUSEL_ITEM_0_READY__");

  private final By scrollTargetLogoLoc = AppiumBy.accessibilityId("WebdriverIO logo");
  private final By scrollTargetTextLoc = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"You found me!!!\")");

  public WebElement swipeScreenEl() {
    return elementUtils.waitForElementToBeVisible(swipeScreenLoc);
  }


  public WebElement carouselContainerEl() {

    return elementUtils.waitForElementToBeVisible(carouselContainerLoc, LONG_EXPLICIT_WAIT);
  }

  public WebElement scrollTargetEl() {

    return elementUtils.waitForElementToBeVisible(scrollTargetLogoLoc, SHORT_EXPLICIT_WAIT);
  }


  public boolean scrollToWebDriverIOLogo() {

//    return swipeUpToElement(scrollTargetLogoLoc, 7);
    return swipeVerticalToElementV2(swipeScreenEl(), carouselContainerLoc, SwipeVerticalDirection.UP, scrollTargetLogoLoc, 6);
  }

  public boolean scrollToScreenTitle() {

//    return swipeDownToElement(swipeScreenTitleLoc, 3);
    return swipeVerticalToElementV2(swipeScreenEl(), carouselContainerLoc, SwipeVerticalDirection.DOWN, swipeScreenTitleLoc, 6);

  }


  public boolean swipeUpToElement(By targetLoc, int maxSwipes) {
    return swipeToElement(SwipeVerticalDirection.UP, targetLoc, maxSwipes);
  }

  public boolean swipeDownToElement(By cardLoc, int maxSwipes) {
    return swipeToElement(SwipeVerticalDirection.DOWN, cardLoc, maxSwipes);
  }

  private boolean swipeToElement(SwipeVerticalDirection direction, By targetLoc, int maxSwipes) {

    boolean isTargetFound = false;

    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      verticalSwipe(swipeVertically, direction);
      if (elementUtils.isElementPresent(targetLoc)) {
        isTargetFound = true;
        break;
      }
    }
    return isTargetFound;
  }


  private boolean swipeVerticalToElementV2(WebElement wrapper, By carouselContainerLoc,
                                           SwipeVerticalDirection direction, By targetLoc, int maxSwipes) {

    boolean isTargetFound = false;
    Rectangle wrapperRect = wrapper.getRect();
    int anchor = wrapperRect.getX() + wrapperRect.getWidth() / 2;
    int wrapperY = wrapperRect.getY();
    int wrapperRectHeight = wrapperRect.getHeight();


    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      int scrollTopY;
      int scrollBottomY;
      WebElement carouselContainerEl = elementUtils.waitForElementToBeVisible(carouselContainerLoc);
      boolean isCarouselVisible = carouselContainerEl != null;

      if (isCarouselVisible) {
        int carouselContainerY = carouselContainerEl.getLocation().getY();
        int bottomDistance = wrapperRectHeight - carouselContainerY - carouselContainerEl.getSize().getHeight();
        if (carouselContainerY >= bottomDistance) {
          scrollTopY = wrapperY + 10;
          scrollBottomY = carouselContainerY - 10;
        } else {
          scrollTopY = carouselContainerY + 10;
          scrollBottomY = wrapperRectHeight - 10;
        }
      } else {
        scrollTopY = wrapperY + 10;
        scrollBottomY = wrapperRectHeight - 10;
      }

      switch (direction) {
        case UP:
          swipeVertically.swipe(anchor, scrollBottomY, anchor, scrollTopY, 60);
          break;
        case DOWN:
          swipeVertically.swipe(anchor, scrollTopY, anchor, scrollBottomY, 60);
          break;
        default:
          throw new RuntimeException("Unsupported direction");
      }


      if (elementUtils.isElementPresent(targetLoc)) {
        isTargetFound = true;
        break;
      }
    }
    return isTargetFound;
  }

  private void verticalSwipe(SwipeVertically swipeVertically, SwipeVerticalDirection direction) {
    switch (direction) {
      case UP:
        swipeVertically.swipeUp();
        break;
      case DOWN:
        swipeVertically.swipeDown();
        break;
      default:
        throw new RuntimeException("Unsupported direction");
    }
  }

  private SwipeVertically initScrollDown(AppiumDriver driver, WebElement wrapper, WebElement childElement) {

    Rectangle wrapperRect = wrapper.getRect();

    int anchor = wrapperRect.getX() + wrapperRect.getWidth() / 2;
    int scrollTopY = wrapperRect.getY() + 5;
    int scrollBottomY = childElement.getLocation().getY() - 10;

    Point start = new Point(anchor, scrollTopY);
    Point end = new Point(anchor, scrollBottomY);


    return new SwipeVertically(driver, start, end, 60);
  }
}

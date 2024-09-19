package screens;

import base.BaseScreen;
import enums.SwipeHorizontalDirection;
import enums.SwipeVerticalDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.gestures.swipe.horizontal.SwipeHorizontally;
import utils.gestures.swipe.vertical.SwipeVertically;

import java.util.List;

import static constants.WaitConstants.DISPLAY_TITLE_CARD_WAIT;
import static constants.WaitConstants.LONG_EXPLICIT_WAIT;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;
import static constants.WaitConstants.VERTICAL_MOVE_IN_MILLIS;

public class SwipeScreen extends BaseScreen {

  private final SwipeHorizontally swipeHorizontal;
  private final SwipeVertically swipeVertically;

  public SwipeScreen(AppiumDriver driver) {

    super(driver);
    verifyScreenLoaded(swipeScreenLoc);

    //TODO: seperate  2 screens

    swipeHorizontal = new SwipeHorizontally(driver, carouselContainerEl(), VERTICAL_MOVE_IN_MILLIS);

    swipeVertically = initScrollDown(driver, swipeScreenEl(), carouselContainerEl());
  }


  private final By swipeScreenLoc = AppiumBy.accessibilityId("Swipe-screen");
  private final By carouselContainerLoc = AppiumBy.accessibilityId("Carousel");
  private final By swipeScreenTitleLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Swipe horizontal\")");

  //    private final By currentCardLoc = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]");
  private final By currentCardLoc = AppiumBy.accessibilityId("card");

  private final By firstCardWrapperLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"__CAROUSEL_ITEM_0_READY__\")");

  //private final By firstCardLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"FULLY OPEN SOURCE\")");
  //NOTE: The requested id selector does not have a package name prefix.
  //private final By firstCardWrapperLoc = AppiumBy.id("__CAROUSEL_ITEM_0_READY__");
  private final By lastCardWrapperLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"__CAROUSEL_ITEM_5_READY__\")");
  private final By currentCardTitleLoc =
    AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer'][1]//android.widget.TextView[1]");
  private final By currentCardDescriptionLoc =
    AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer'][1]//android.widget.TextView[2]");
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

  private WebElement firstCardEl() {
    return elementUtils.waitForFindingElement(firstCardWrapperLoc, SHORT_EXPLICIT_WAIT);
  }

  private WebElement currentCardEl() {
    try {
      return elementUtils.waitForFindingElement(currentCardLoc, SHORT_EXPLICIT_WAIT);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private WebElement currentCardTitleEl() {
    return currentCardEl().findElement(currentCardTitleLoc);
  }

  private WebElement currentCardDescriptionEl() {
    return currentCardEl().findElement(currentCardDescriptionLoc);
  }


  private WebElement lastCardEl() {
    return elementUtils.waitForFindingElement(lastCardWrapperLoc);
  }

  public SwipeScreen verifySwipeScreenDisplayed() {
//TODO : not assert in screen object
    Assert.assertTrue(elementUtils.isElementDisplayed(swipeScreenLoc));
    return this;
  }

  public SwipeScreen verifySwipeScreenTitle(String expectTitle) {
//TODO : not assert in screen object

    Assert.assertTrue(elementUtils.waitForTextPresentInElement(swipeScreenTitleLoc, expectTitle));
    return this;
  }

  public SwipeScreen verifyCarouselDisplayed() {
//TODO : not assert in screen object

    Assert.assertTrue(elementUtils.isElementDisplayed(carouselContainerEl()), "The carousel is not displayed");
    return this;
  }

  public boolean swipeRightToCardTitle(String targetTitle, int maxSwipes) {
    return swipeRightToCard(targetTitle, maxSwipes);
  }

  public boolean swipeLeftToCardTitle(String targetTitle, int maxSwipes) {
    return swipeToElement(SwipeHorizontalDirection.LEFT, targetTitle, maxSwipes);
  }

  public boolean swipeRightToCard(String targetTitle, int maxSwipes) {
    return swipeToElement(SwipeHorizontalDirection.RIGHT, targetTitle, maxSwipes);
  }

  public boolean swipeLeftToElement(By targetLoc, int maxSwipes) {
    return swipeToElement(SwipeHorizontalDirection.LEFT, targetLoc, maxSwipes);
  }

  public boolean swipeRightToElement(By cardLoc, int maxSwipes) {
    return swipeToElement(SwipeHorizontalDirection.RIGHT, cardLoc, maxSwipes);
  }


  public boolean swipeLeftToElement(WebElement targetEl, int maxSwipes) {
    return swipeToElement(SwipeHorizontalDirection.LEFT, targetEl, maxSwipes);
  }

  public boolean swipeRightToElement(WebElement targetEl, int maxSwipes) {
    return swipeToElement(SwipeHorizontalDirection.RIGHT, targetEl, maxSwipes);

  }

  private boolean swipeToElement(SwipeHorizontalDirection direction, String targetTitle, int maxSwipes) {

    boolean isTargetFound = false;

    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      horizontalSwipe(swipeHorizontal, direction);
      if (elementUtils.isElementPresentText(currentCardTitleLoc, targetTitle, DISPLAY_TITLE_CARD_WAIT)) {
        isTargetFound = true;
        break;
      }
    }

    return isTargetFound;
  }


  private WebElement getCurrentCardEl(By locator) {

    List<WebElement> elements = driver.findElements(locator);
    if (elements.isEmpty()) {
      Assert.fail("Card should be present");
    }
    return elements.get(0);
  }

  private boolean swipeToElement(SwipeHorizontalDirection direction, By targetLoc, int maxSwipes) {

    boolean isTargetFound = false;

    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      horizontalSwipe(swipeHorizontal, direction);
      if (elementUtils.isElementPresent(targetLoc)) {
        isTargetFound = true;
        break;
      }
    }
    return isTargetFound;
  }

  private boolean swipeToElement(SwipeHorizontalDirection direction, WebElement carouselContainerEl, int maxSwipes) {

//        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
    boolean isTargetFound = false;

    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      horizontalSwipe(swipeHorizontal, direction);
      if (elementUtils.isElementDisplayed(carouselContainerEl)) {
        isTargetFound = true;
        break;
      }
    }
    return isTargetFound;
  }

  private void horizontalSwipe(SwipeHorizontally swipeHorizontal, SwipeHorizontalDirection direction) {
    switch (direction) {
      case LEFT:
        swipeHorizontal.swipeLeft();
        break;
      case RIGHT:
        swipeHorizontal.swipeRight();
        break;
      default:
        throw new RuntimeException("Unsupported direction");
    }
  }

  public SwipeScreen swipeLeft(int maxSwipes) {
    SwipeHorizontally swipeHorizontal = new SwipeHorizontally(driver, carouselContainerEl());
    swipeHorizontal.swipeLeft(maxSwipes);
    return this;
  }

  public SwipeScreen swipeRight(int maxSwipes) {
    SwipeHorizontally swipeHorizontal = new SwipeHorizontally(driver, carouselContainerEl());
    swipeHorizontal.swipeRight(maxSwipes);
    return this;
  }

  public boolean goToTheFirstCard(int maxSwipes) {

    // TODO: improve get last card by the way that no left card before current card

    boolean isFirstItem = isFirstCard();
    if (!isFirstItem) {
      isFirstItem = swipeRightToElement(firstCardWrapperLoc, maxSwipes);
    }

    return isFirstItem;
  }

  private boolean isFirstCard() {
    By currentCardWrapperLoc = getCurrentCardWrapperLoc();

    return firstCardWrapperLoc.equals(currentCardWrapperLoc);
  }

  public boolean goToLastCard(int maxSwipes) {

    // TODO: improve get last card by the way that no right card after current card

    boolean isLastItem = isLastCard();
    if (!isLastItem) {
      isLastItem = swipeLeftToElement(lastCardWrapperLoc, maxSwipes);
    }
    return isLastItem;
  }

  private boolean isLastCard() {
    By currentCardWrapperLoc = getCurrentCardWrapperLoc();

    return lastCardWrapperLoc.equals(currentCardWrapperLoc);
  }


  private By getCurrentCardWrapperLoc() {

    WebElement currentCardWrapperEl = driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]"));
    String resourceId = currentCardWrapperEl.getAttribute("resourceId");

    return AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + resourceId + "\")");
  }

  public void verifyCardContent(String expectedTitle, String expectedDescription) {

    verifyCardTitle(expectedTitle);
    verifyCardDescription(expectedDescription);
  }

  private void verifyCardTitle(String expectedTitle) {
    String actualTitle = currentCardTitleEl().getText();
    Assert.assertEquals(actualTitle, expectedTitle, "Title is not correct");
  }

  private void verifyCardDescription(String expectedDescription) {
    String actualDescription = currentCardDescriptionEl().getText();
    Assert.assertEquals(actualDescription, expectedDescription, "Description is not correct");
  }

  public boolean scrollToWebDriverIOLogo() {

//    return swipeUpToElement(scrollTargetLogoLoc, 7);
    return swipeDownToLogo(swipeScreenEl(), carouselContainerLoc, SwipeVerticalDirection.UP, scrollTargetLogoLoc, 6);
  }

  public boolean scrollToScreenTitle() {

//    return swipeDownToElement(swipeScreenTitleLoc, 3);
    return swipeUpToTitle(swipeScreenEl(), carouselContainerLoc, SwipeVerticalDirection.DOWN, swipeScreenTitleLoc, 6);

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


  private boolean swipeUpToTitle(WebElement wrapper, By carouselContainerLoc,
                                 SwipeVerticalDirection direction, By targetLoc, int maxSwipes) {

    boolean isTargetFound = false;
    Rectangle wrapperRect = wrapper.getRect();
    int anchor = wrapperRect.getX() + wrapperRect.getWidth() / 2;
    int wrapperY = wrapperRect.getY();
    int wrapperRectHeight = wrapperRect.getHeight();


    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      int scrollTopY;
      int scrollBottomY;
      WebElement carouselContainerEl = elementUtils.waitForElementToBeVisible(carouselContainerLoc, SHORT_EXPLICIT_WAIT);
      boolean isCarouselVisible = carouselContainerEl != null;

      if (!isCarouselVisible) {
        scrollTopY = wrapperY + 10;
        scrollBottomY = wrapperRectHeight - 10;
      } else {
        int carouselContainerY = carouselContainerEl.getLocation().getY();
        int bottomDistance = wrapperRectHeight - carouselContainerY - carouselContainerEl.getSize().getHeight();
        if (carouselContainerY >= bottomDistance) {
          scrollTopY = wrapperY + 10;
          scrollBottomY = carouselContainerY - 10;
        } else {
          scrollTopY = carouselContainerY + 10;
          scrollBottomY = wrapperRectHeight - 10;
        }

      }

      swipeVertically.swipe(anchor, scrollTopY, anchor, scrollBottomY, 60);


      if (elementUtils.isElementPresent(targetLoc)) {
        isTargetFound = true;
        break;
      }
    }
    return isTargetFound;
  }

  private boolean swipeDownToLogo(WebElement wrapper, By carouselContainerLoc,
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

      swipeVertically.swipe(anchor, scrollBottomY, anchor, scrollTopY, 60);


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

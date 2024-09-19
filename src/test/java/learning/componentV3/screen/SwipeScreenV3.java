package learning.componentV3.screen;

import enums.SwipeHorizontalDirection;
import enums.SwipeVerticalDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import learning.componentV3.components.CarouselComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.gestures.swipe.horizontal.SwipeHorizontally;
import utils.gestures.swipe.vertical.SwipeVertically;

import static constants.WaitConstants.DISPLAY_TITLE_CARD_WAIT;
import static constants.WaitConstants.SWIPE_SHORT_EXPLICIT_WAIT;
import static constants.WaitConstants.VERTICAL_MOVE_IN_MILLIS;

public class SwipeScreenV3 extends BaseScreenV3 {

  //  private final ContentDescSwipeComponent contentDescSwipeComp;
  private final CarouselComponent carouselComponent;
  private final SwipeHorizontally swipeHorizontal;
  private final SwipeVertically swipeVertically;

//  private WebElement

  public SwipeScreenV3(AppiumDriver driver) {

    super(driver);

//    this.contentDescSwipeComp = findComponent(ContentDescSwipeComponent.class);
//    this.carouselComponent = findComponent(CarouselComponent.class);
    this.carouselComponent = new CarouselComponent(driver, carouselEl());
    verifyScreenLoaded(swipeScreenTitleEl());

    swipeHorizontal = new SwipeHorizontally(driver, this.carouselComponent.getCarouselEl(), VERTICAL_MOVE_IN_MILLIS);
    swipeVertically = initSwipeVertically(driver, swipeScreenEl(), this.carouselComponent.getCarouselEl());
//    swipeVertically = initScrollDown(driver, swipeScreenEl(), carouselContainerEl());
//
  }

  private WebElement carouselEl() {

    return elementUtils.waitForElementToBeVisible(AppiumBy.accessibilityId("Carousel"));
  }

  private final By swipeScreenLoc = AppiumBy.accessibilityId("Swipe-screen");

  public WebElement swipeScreenEl() {
    return elementUtils.waitForElementToBeVisible(swipeScreenLoc);
  }

  private final By swipeScreenTitleLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Swipe horizontal\")");
  private final By scrollTargetLogoLoc = AppiumBy.accessibilityId("WebdriverIO logo");

  public WebElement scrollTargetEl() {

//    return elementUtils.waitForElementToBeVisible(this.carouselEl(), scrollTargetLogoLoc, 10);
    return elementUtils.waitForElementToBeVisible(scrollTargetLogoLoc, SWIPE_SHORT_EXPLICIT_WAIT);
  }

  public final WebElement swipeScreenTitleEl() {
    return elementUtils.waitForFindingElement(swipeScreenTitleLoc, SWIPE_SHORT_EXPLICIT_WAIT);

  }

  public String getScreenTitle(String expectTitle) {
    return swipeScreenTitleEl().getText();
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
//      if (elementUtils.isElementPresentText(this.contentDescSwipeComp.currentCardTitleEl(), targetTitle, DISPLAY_TITLE_CARD_WAIT)) {
      if (elementUtils.isElementPresentText(this.carouselComponent.getCurrentCardTitleLoc(), targetTitle, DISPLAY_TITLE_CARD_WAIT)) {
        isTargetFound = true;
        break;
      }

//      if (scrollToCardTitle(targetTitle)) {
//        isTargetFound = true;
//        break;
//      }
    }

    return isTargetFound;
  }


//  private WebElement getCurrentCardEl(By locator) {
//
//    List<WebElement> elements = driver.findElements(locator);
//    if (elements.isEmpty()) {
//      Assert.fail("Card should be present");
//    }
//    return elements.get(0);
//  }

  private boolean swipeToElement(SwipeHorizontalDirection direction, By targetLoc, int maxSwipes) {

    boolean isTargetFound = false;

    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      horizontalSwipe(swipeHorizontal, direction);
//      if (elementUtils.isElementPresent(this.contentDescSwipeComp.carouselEl(), targetLoc)) {
//        isTargetFound = true;
//        break;
//      }

//      if (elementUtils.isElementPresent(this.carouselComponent.getCarouselEl(), targetLoc)) {
      if (elementUtils.isElementPresent(targetLoc)) {
        isTargetFound = true;
        break;
      }
    }
    return isTargetFound;
  }

  private boolean swipeToElement(SwipeHorizontalDirection direction, WebElement targetEl, int maxSwipes) {

//        SwipeHorizontal swipeHorizontal = new SwipeHorizontal(driver, carouselContainerEl());
    boolean isTargetFound = false;

    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      horizontalSwipe(swipeHorizontal, direction);
      if (elementUtils.isElementDisplayed(targetEl)) {
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

  public SwipeScreenV3 swipeLeft(int maxSwipes) {
    SwipeHorizontally swipeHorizontal = new SwipeHorizontally(driver, this.carouselComponent.getCarouselEl());
    swipeHorizontal.swipeLeft(maxSwipes);
    return this;
  }

  public SwipeScreenV3 swipeRight(int maxSwipes) {
    SwipeHorizontally swipeHorizontal = new SwipeHorizontally(driver, this.carouselComponent.getCarouselEl());
    swipeHorizontal.swipeRight(maxSwipes);
    return this;
  }

  public boolean goToTheFirstCard(int maxSwipes) {

    // TODO: improve get last card by the way that no left card before current card

    boolean isFirstItem = isFirstCard();
    if (!isFirstItem) {
      isFirstItem = swipeRightToElement(this.carouselComponent.getFirstCardWrapperLoc(), maxSwipes);
    }

    return isFirstItem;
  }

  private boolean isFirstCard() {
    By currentCardWrapperLoc = getCurrentCardWrapperLoc();

    return this.carouselComponent.getFirstCardWrapperLoc().equals(currentCardWrapperLoc);
  }

  public boolean goToLastCard(int maxSwipes) {

    // TODO: improve get last card by the way that no right card after current card

    boolean isLastItem = isLastCard();
    if (!isLastItem) {
      isLastItem = swipeLeftToElement(this.carouselComponent.getLastCardWrapperLoc(), maxSwipes);
    }
    return isLastItem;
  }

  private boolean isLastCard() {
    By currentCardWrapperLoc = getCurrentCardWrapperLoc();

    return this.carouselComponent.getLastCardWrapperLoc().equals(currentCardWrapperLoc);
  }


  private By getCurrentCardWrapperLoc() {

//    WebElement currentCardWrapperEl = driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]/.."));
    WebElement currentCardWrapperEl =
      this.carouselComponent.getCarouselEl().findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]/.."));
    String resourceId = currentCardWrapperEl.getAttribute("resourceId");

    return AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + resourceId + "\")");
  }

  public void verifyCardContent(String expectedTitle, String expectedDescription) {

    verifyCardTitle(expectedTitle);
    verifyCardDescription(expectedDescription);
  }

  private void verifyCardTitle(String expectedTitle) {
    String actualTitle = this.carouselComponent.currentCardTitleEl().getText();
    Assert.assertEquals(actualTitle, expectedTitle, "Title is not correct");
  }

  private void verifyCardDescription(String expectedDescription) {
    String actualDescription = this.carouselComponent.currentCardDescriptionEl().getText();
    Assert.assertEquals(actualDescription, expectedDescription, "Description is not correct");
  }

  public boolean scrollToWebDriverIOLogo() {

    return swipeUpToElement(scrollTargetEl(), 3);
  }

//  private final By swipeScreenTitleLoc = AppiumBy.androidUIAutomator("new UiSelector(). textContains(\"Swipe horizontal\")");

  public boolean scrollToScreenTitle() {

    return swipeDownToElement(swipeScreenTitleLoc, 3);
  }


  public boolean scrollToCardTitle(String title) {
    return this.carouselComponent.targetCardTitleEl(title) != null;
  }

  public boolean swipeUpToElement(By targetLoc, int maxSwipes) {
    return swipeToElement(SwipeVerticalDirection.UP, targetLoc, maxSwipes);
  }


  public boolean swipeUpToElement(WebElement targetEl, int maxSwipes) {
    return swipeToElement(SwipeVerticalDirection.UP, targetEl, maxSwipes);
  }

  public boolean swipeDownToElement(By cardLoc, int maxSwipes) {
    return swipeToElement(SwipeVerticalDirection.DOWN, cardLoc, maxSwipes);
  }

  private boolean swipeToElement(SwipeVerticalDirection direction, By targetLoc, int maxSwipes) {

    boolean isTargetFound = false;

    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      verticalSwipe(swipeVertically, direction);
      if (elementUtils.isElementPresent(this.carouselComponent.getCarouselEl(), targetLoc)) {
        isTargetFound = true;
        break;
      }
    }
    return isTargetFound;
  }

  private boolean swipeToElement(SwipeVerticalDirection direction, WebElement targetEl, int maxSwipes) {

    boolean isTargetFound = false;

    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      verticalSwipe(swipeVertically, direction);
      if (elementUtils.isElementDisplayed(targetEl)) {
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

  private SwipeVertically initSwipeVertically(AppiumDriver driver, WebElement wrapper, WebElement childElement) {

    Rectangle wrapperRect = wrapper.getRect();

    int anchor = wrapperRect.getX() + wrapperRect.getWidth() / 2;
    int scrollTopY = wrapperRect.getY() + 100;
    int scrollBottomY = childElement.getLocation().getY() - 100;

    Point start = new Point(anchor, scrollTopY);
    Point end = new Point(anchor, scrollBottomY);

    return new SwipeVertically(driver, start, end, 60);
  }
}

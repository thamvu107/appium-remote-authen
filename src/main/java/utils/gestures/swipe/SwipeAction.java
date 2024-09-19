package utils.gestures.swipe;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.ScreenSizeUtils;

import java.time.Duration;
import java.util.Collections;

import static constants.SwipeConstants.DEFAULT_ANCHOR_PERCENT;
import static constants.SwipeConstants.DEFAULT_LARGER_PERCENT;
import static constants.SwipeConstants.DEFAULT_SMALL_PERCENT;
import static constants.SwipeConstants.MOVE_DURATION;
import static constants.WaitConstants.QUICK_PAUSE;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

@Slf4j
public abstract class SwipeAction {

  protected AppiumDriver driver;
  protected long moveDuration;
  protected WebElement wrapperElement;
//    protected Point location;
//    protected Dimension dimension;

  protected Rectangle wrapperBounds;
  protected float anchorPercent;
  protected float smallerPercent;
  protected float largerPercent;
  protected int anchor;
  protected int startCoordinate;
  protected int endCoordinate;

  public SwipeAction(AppiumDriver driver) {
    setDriver(driver);
    setDefaultPercents();
    setDefaultDurations();
    setWrapperBounds();
  }

  public SwipeAction(AppiumDriver driver, WebElement wrapperElement) {
    setDriver(driver);
    setDefaultPercents();
    setDefaultDurations();
    setWrapperElement(wrapperElement);
    setWrapperBounds();
  }

  public SwipeAction(AppiumDriver driver, long moveDuration) {
    setDriver(driver);
    setDefaultPercents();
    setMoveDuration(moveDuration);
    setWrapperBounds();
  }

  public SwipeAction(AppiumDriver driver, WebElement wrapperElement, long moveDuration) {
    setDriver(driver);
    setWrapperElement(wrapperElement);
    setMoveDuration(moveDuration);
    setDefaultPercents();
    setWrapperBounds();
  }

  public SwipeAction(AppiumDriver driver, float anchorPercent, float smallerPercent, float largerPercent, long moveDuration) {
    setDriver(driver);
    setSmallerPercent(smallerPercent);
    setLargerPercent(largerPercent);
    setAnchorPercent(anchorPercent);
    setMoveDuration(moveDuration);
    setWrapperBounds();
  }

  public SwipeAction(AppiumDriver driver, WebElement wrapperElement, float anchorPercent, float smallerPercent, float largerPercent,
                     long moveDuration) {
    setDriver(driver);
    setWrapperElement(wrapperElement);
    setSmallerPercent(smallerPercent);
    setLargerPercent(largerPercent);
    setAnchorPercent(anchorPercent);
    setMoveDuration(moveDuration);
    setWrapperBounds();
  }


  public SwipeAction(AppiumDriver driver, Point start, Point end, long moveDuration) {
    setDriver(driver);
    // Validate start and end points
    setMoveDuration(moveDuration);
  }


  public void swipe(int startX, int startY, int endX, int endY, long moveDuration) {

    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1)
      .addAction(finger.createPointerMove(Duration.ZERO, viewport(), startX, startY))
      .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
      .addAction(new Pause(finger, ofMillis(QUICK_PAUSE)))
      .addAction(finger.createPointerMove(ofMillis(moveDuration), viewport(), endX, endY))
      .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
      .addAction(new Pause(finger, ofMillis(QUICK_PAUSE)));


    driver.perform(Collections.singletonList(swipe));
  }

  private void setDriver(AppiumDriver driver) {
    validateNotNull(driver, "AppiumDriver must not be null");
    this.driver = driver;
  }

  private void setDefaultPercents() {
    setAnchorPercent(DEFAULT_ANCHOR_PERCENT);
    setSmallerPercent(DEFAULT_SMALL_PERCENT);
    setLargerPercent(DEFAULT_LARGER_PERCENT);
  }

  private void setDefaultDurations() {
    setMoveDuration(MOVE_DURATION);
  }

  private void setWrapperElement(WebElement wrapper) {
    validateNotNull(wrapper, "Wrapper element must not be null");
    this.wrapperElement = wrapper;
  }

  private void setSmallerPercent(float smallerPercent) {
    validateSwipePercentCoordinates(smallerPercent);
    this.smallerPercent = smallerPercent;
  }

  private void setLargerPercent(float endPercentage) {
    validateSwipePercentCoordinates(endPercentage);
    this.largerPercent = endPercentage;
  }

  private void setAnchorPercent(float anchorPercent) {
    validateSwipePercentCoordinates(anchorPercent);
    this.anchorPercent = anchorPercent;
  }

  private void setMoveDuration(long moveDuration) {
    validateSpeed(moveDuration);
    this.moveDuration = moveDuration;
  }

  private void setWrapperBounds() {
    if (this.wrapperElement == null) {
      Point point = new Point(0, 0);
      Dimension screenSize = new ScreenSizeUtils(this.driver).getDimension();
      this.wrapperBounds = new Rectangle(point, screenSize);
    } else {
      this.wrapperBounds = this.wrapperElement.getRect();
    }
  }

  protected void validateSwipePercentCoordinates(float percent) {
    if (percent < 0 || percent > 1) {
      log.atError().setMessage("Swipe percentage must be between 0 and 1 inclusive. Given percentage: " + percent).log();
      throw new IllegalArgumentException("Swipe percentage must be between 0 and 1 inclusive. Given percentage: " + percent);
    }
  }

  private void validateSpeed(long moveDuration) {
    if (moveDuration <= 0) {
      log.atError().setMessage("moveDuration must be positive.").log();
      throw new IllegalArgumentException("moveDuration must be positive.");
    }
  }

  private void validateNotNull(Object param, String message) {
    if (param == null) {
      log.atError().setMessage(message).log();
      throw new IllegalArgumentException(message);
    }
  }

  protected abstract int calculateAnchor();

  protected abstract int calculateSmallCoordinate();

  protected abstract int calculateLargeCoordinate();
}

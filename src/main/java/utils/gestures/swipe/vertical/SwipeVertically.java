package utils.gestures.swipe.vertical;

import enums.SwipeVerticalDirection;
import exceptions.swipe.vertical.SwipeDownException;
import exceptions.swipe.vertical.SwipeUpException;
import exceptions.swipe.vertical.SwipeVerticallyException;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import utils.gestures.swipe.SwipeAction;

@Slf4j
public class SwipeVertically extends SwipeAction {

  protected int topY;
  protected int bottomY;

  public SwipeVertically(AppiumDriver driver) {

    super(driver);
    this.anchor = this.calculateAnchor();
    this.topY = this.calculateSmallCoordinate();
    this.bottomY = this.calculateLargeCoordinate();
  }

  public SwipeVertically(AppiumDriver driver, WebElement wrapper) {

    super(driver, wrapper);
    this.anchor = this.calculateAnchor();
    this.topY = this.calculateSmallCoordinate();
    this.bottomY = this.calculateLargeCoordinate();
  }


  public SwipeVertically(AppiumDriver driver, long moveDuration) {

    super(driver, moveDuration);
    this.anchor = this.calculateAnchor();
    this.topY = this.calculateSmallCoordinate();
    this.bottomY = this.calculateLargeCoordinate();
  }

  public SwipeVertically(AppiumDriver driver, WebElement wrapper, long moveDuration) {

    super(driver, wrapper, moveDuration);
    this.anchor = this.calculateAnchor();
    this.topY = this.calculateSmallCoordinate();
    this.bottomY = this.calculateLargeCoordinate();
  }

  public SwipeVertically(AppiumDriver driver, float anchorPercentage, float topPercent, float bottomPercent, long moveDuration) {

    super(driver, anchorPercentage, topPercent, bottomPercent, moveDuration);
    this.anchor = this.calculateAnchor();
    this.topY = this.calculateSmallCoordinate();
    this.bottomY = this.calculateLargeCoordinate();
    log.atInfo()
      .log("Swipe vertical: anchorPercentage: " + anchorPercentage + " topPercent: " + topPercent + " bottomPercent: " + bottomPercent);
  }

  public SwipeVertically(AppiumDriver driver, WebElement wrapper, float anchorPercentage, float topPercent, float bottomPercent,
                         long moveDuration) {

    super(driver, wrapper, anchorPercentage, topPercent, bottomPercent, moveDuration);
    this.anchor = this.calculateAnchor();
    this.topY = this.calculateSmallCoordinate();
    this.bottomY = this.calculateLargeCoordinate();
    log.atInfo()
      .log("Swipe vertical: anchorPercentage: " + anchorPercentage + " topPercent: " + topPercent + " bottomPercent: " + bottomPercent);
  }

  public SwipeVertically(AppiumDriver drive, Point start, Point end, long moveDuration) {

    super(drive, start, end, moveDuration);
    this.anchor = start.getX();
    this.topY = start.getY();
    this.bottomY = end.getY();
    log.atInfo().log("Swipe vertical: anchor: " + anchor + " topY: " + topY + " bottomY: " + bottomY);
  }

  public SwipeVertically(AppiumDriver driver, WebElement wrapper, WebElement childElement) {


    super(driver, wrapper);
    this.anchor = this.calculateAnchor();
    this.topY = this.calculateSmallCoordinate();
    this.bottomY = this.calculateLargeCoordinate();
  }

//  private SwipeVertically initScrollDown(AppiumDriver driver, WebElement wrapper, WebElement childElement) {
//
//    Rectangle wrapperRect = wrapper.getRect();
//
//    int anchor = wrapperRect.getX() + wrapperRect.getWidth() / 2;
//    int scrollTopY = wrapperRect.getY() + 5;
//    int scrollBottomY = childElement.getLocation().getY() - 10;
//
//    Point start = new Point(anchor, scrollTopY);
//    Point end = new Point(anchor, scrollBottomY);
//
//
//    return new SwipeVertically(driver, start, end, 60);
//  }


  @Override
  protected int calculateAnchor() {

    return wrapperBounds.getX() + Math.round(wrapperBounds.getWidth() * anchorPercent);
  }

  @Override
  protected int calculateSmallCoordinate() {
    return wrapperBounds.getY() + Math.round(wrapperBounds.getHeight() * smallerPercent);
  }

  @Override
  protected int calculateLargeCoordinate() {
    return wrapperBounds.getY() + Math.round(wrapperBounds.getHeight() * largerPercent);
  }

  public void swipeUp() {
    performVerticalSwipe(SwipeVerticalDirection.UP);
    log.atInfo().log("Swipe up");
  }

  public void swipeDown() {
    performVerticalSwipe(SwipeVerticalDirection.DOWN);
    log.atInfo().log("Swipe down");
  }

  private void performVerticalSwipe(SwipeVerticalDirection direction) {

    setYCoordinates(direction);
    swipe(anchor, this.startCoordinate, anchor, this.endCoordinate, moveDuration);
  }

  private void setYCoordinates(SwipeVerticalDirection direction) {
    switch (direction) {
      case UP:
        this.startCoordinate = bottomY;
        this.endCoordinate = topY;
        if (startCoordinate <= endCoordinate) {
          log.atError().log(
            "The start percentage should be greater than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
          throw new SwipeUpException(
            "The start percentage should be greater than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
        }
        break;
      case DOWN:
        this.startCoordinate = topY;
        this.endCoordinate = bottomY;
        if (startCoordinate >= endCoordinate) {
          log.atError()
            .log("The start percentage should be less than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
          throw new SwipeDownException(
            "The start percentage should be less than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
        }
        break;
      default:
        log.atError().setMessage("Unsupported swipe direction: " + direction).log();
        throw new SwipeVerticallyException("Unsupported swipe direction: " + direction);
    }
  }
}

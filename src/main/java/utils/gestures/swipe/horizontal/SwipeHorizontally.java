package utils.gestures.swipe.horizontal;

import enums.SwipeHorizontalDirection;
import exceptions.swipe.horizontal.SwipeHorizontalException;
import exceptions.swipe.horizontal.SwipeLeftException;
import exceptions.swipe.horizontal.SwipeRightException;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import utils.gestures.swipe.SwipeAction;

public class SwipeHorizontally extends SwipeAction {
  protected int leftX;
  protected int rightX;

  public SwipeHorizontally(AppiumDriver driver) {
    super(driver);
    calculateCoordinates();
  }

  public SwipeHorizontally(AppiumDriver driver, WebElement wrapper) {
    super(driver, wrapper);
    calculateCoordinates();
  }

  public SwipeHorizontally(AppiumDriver driver, long moveDuration) {
    super(driver, moveDuration);
    calculateCoordinates();
  }

  // TODO: Swipe on Component
  public SwipeHorizontally(AppiumDriver driver, WebElement wrapper, long moveDuration) {

    super(driver, wrapper, moveDuration);
    calculateCoordinates();
  }

  public SwipeHorizontally(AppiumDriver driver, float anchorPercent, float smallerPercent, float largerPercent, long moveDuration) {
    super(driver, anchorPercent, smallerPercent, largerPercent, moveDuration);
    calculateCoordinates();
  }

  @Override
  protected int calculateAnchor() {
    return wrapperBounds.getY() + Math.round(wrapperBounds.getHeight() * anchorPercent);
  }

  @Override
  protected int calculateSmallCoordinate() {
    return wrapperBounds.getX() + Math.round(wrapperBounds.getWidth() * smallerPercent);
  }

  @Override
  protected int calculateLargeCoordinate() {
    return wrapperBounds.getX() + Math.round(wrapperBounds.getWidth() * largerPercent);
  }


  public void swipeLeft() {
    performHorizontalSwipe(SwipeHorizontalDirection.LEFT);
  }

  public void swipeRight() {
    performHorizontalSwipe(SwipeHorizontalDirection.RIGHT);
  }

  public void swipeLeft(int maxSwipes) {
    swipeMultiTimes(SwipeHorizontalDirection.LEFT, maxSwipes);
  }

  public void swipeRight(int maxSwipes) {
    swipeMultiTimes(SwipeHorizontalDirection.RIGHT, maxSwipes);
  }

  private void swipeMultiTimes(SwipeHorizontalDirection direction, int maxSwipes) {
    for (int swipeCounter = 0; swipeCounter < maxSwipes; swipeCounter++) {
      if (direction == SwipeHorizontalDirection.LEFT) {
        swipeLeft();
      } else {
        swipeRight();
      }
    }
  }

  private void performHorizontalSwipe(SwipeHorizontalDirection direction) {
    setXCoordinates(direction);
    swipe(startCoordinate, anchor, endCoordinate, anchor, moveDuration);
  }

  private void setXCoordinates(SwipeHorizontalDirection direction) {
    switch (direction) {
      case LEFT:
        this.startCoordinate = rightX;
        this.endCoordinate = leftX;
        if (startCoordinate <= endCoordinate) {
          throw new SwipeLeftException(
            "The start percentage should be greater than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
        }
        break;

      case RIGHT:
        this.startCoordinate = leftX;
        this.endCoordinate = rightX;
        if (startCoordinate >= endCoordinate) {
          throw new SwipeRightException(
            "The start percentage should be less than the end percentage. Start: " + startCoordinate + ", End: " + endCoordinate);
        }
        break;

      default:
        throw new SwipeHorizontalException("Unsupported swipe direction: " + direction);
    }
  }

  private void calculateCoordinates() {
    this.anchor = this.calculateAnchor();
    this.leftX = this.calculateSmallCoordinate();
    this.rightX = this.calculateLargeCoordinate();
  }
}

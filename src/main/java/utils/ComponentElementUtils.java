package utils;

import exceptions.WaitForConditionException;
import exceptions.WaitForElementException;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class ComponentElementUtils {
  private final AppiumDriver driver;
  //  private final PlatformType currentPlatform;
  public WaitUtils waitUtils;
  private final WebElement componentEl;

  public ComponentElementUtils(AppiumDriver driver, WebElement componentEl) {
    this.driver = driver;
    this.componentEl = componentEl;
//    this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
    this.waitUtils = new WaitUtils(this.driver);
  }

  public WebElement waitForElementToBeVisible(By locator) {

    try {
      return waitUtils.explicitWait()
        .until(ExpectedConditions.visibilityOfElementLocated(locator));
    } catch (TimeoutException e) {
      String message = String.format("Timeout waiting for element to be visible: %s", locator);
      log.atError().setMessage(message).setCause(e).log();
      //TODO: retry maxTimes = 2 to wait app load home pages
      throw new TimeoutException(message, e);
    } catch (Exception e) {
      String message = String.format("An unexpected error occurred while waiting for element to be visible: %s", locator);
      log.atError().setMessage(message).setCause(e).log();
      throw new RuntimeException(message, e);
    }
  }

  public WebElement waitForFindingElement(By childLocator) {
    try {
      return waitForCondition(component -> {
        if (component != null) {
          return componentEl.findElement(childLocator);
        }
        return null;
      });
    } catch (Exception e) {
      String errorMessage = String.format("Exception occurred while waiting for elements: %s", childLocator);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new RuntimeException(errorMessage, e);
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      return !componentEl.findElements(locator).isEmpty();
    } catch (Exception e) {
      log.atError().log("isElementPresent " + e.getMessage());
      return false;
    }
  }

  public WebElement waitForElementTobeClickable(By locator) {

    try {
      WebElement element = waitUtils.explicitWait()
        .until(ExpectedConditions.elementToBeClickable(locator));

      if (element == null) {
        String errorMessage = String.format("Element not found while waiting to be clickable: %s", locator);
        log.atError().setMessage(errorMessage).log();
        throw new WaitForElementException(errorMessage);
      }

      return element;

    } catch (Exception e) {
      String errorMessage = String.format("Unexpected exception while waiting for element to be clickable: %s", locator);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new WaitForElementException(errorMessage, e);
    }

  }


  //TODO : function interface
  private <T> T waitForCondition(ExpectedCondition<T> condition) {
    try {
      return waitUtils.explicitWait()
        .until(condition);
    } catch (Exception e) {
      String errorMessage = String.format("Timeout waiting for condition: %s", condition);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new WaitForConditionException(errorMessage, e);
    }
  }


//  public By getLocator(Map<PlatformType, By> locatorMap) {
//
//    if (locatorMap == null) {
//      log.atError().log("Locator cannot be null");
//      throw new IllegalArgumentException("Locator cannot be null");
//    }
//
//    return locatorMap.get(currentPlatform);
//  }
}

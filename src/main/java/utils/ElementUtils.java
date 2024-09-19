package utils;

import enums.PlatformType;
import exceptions.WaitForConditionException;
import exceptions.WaitForElementException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;


@Slf4j
public class ElementUtils {
  private final AppiumDriver driver;
  //  private WebElement componentEl;
  private AndroidDriver androidDriver;
  private IOSDriver iosDriver;
  public WaitUtils waitUtils;
  private final PlatformType currentPlatform;
//    private static final Logger logger = LoggerFactory.getLogger(ElementUtils.class);

  public ElementUtils(AppiumDriver driver) {
    this.driver = driver;
    this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
    this.waitUtils = new WaitUtils(this.driver);

  }

//  public ElementUtils(AppiumDriver driver, By componentLocator) {
//    this.driver = driver;
//    this.componentEl = this.waitForDriverFindingElement(componentLocator);
////    this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
//    this.waitUtils = new WaitUtils(this.driver);
//  }
//
//
//  public ElementUtils(AppiumDriver driver, WebElement componentElement) {
//    this.driver = driver;
//    this.componentEl = componentElement;
////    this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
//    this.waitUtils = new WaitUtils(this.driver);
//  }

  public By getLocator(Map<PlatformType, By> locatorMap) {

    if (locatorMap == null) {
      log.atError().log("Locator cannot be null");
      throw new IllegalArgumentException("Locator cannot be null");
    }

    return locatorMap.get(currentPlatform);
  }

  public boolean isElementDisplayed(By locator) {
    try {
      WebElement element = this.waitForElementToBeVisible(locator);
      return element.isDisplayed();
    } catch (Exception e) {
      log.atError().log("isElementDisplayed " + e.getMessage());
      return false;
    }
  }

  public boolean isElementDisplayed(By locator, long waitTimeInMils) {
    try {
      WebElement element = this.waitForElementToBeVisible(locator, waitTimeInMils);
      return element.isDisplayed();
    } catch (Exception e) {
      log.atError().log("isElementDisplayed " + e.getMessage());
      return false;
    }
  }

  public boolean isElementDisplayed(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (Exception e) {
      log.atError().log("isElementDisplayed " + e.getMessage());
      return false;
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      return !driver.findElements(locator).isEmpty();
    } catch (Exception e) {
      log.atError().log("isElementPresent " + e.getMessage());
      return false;
    }
  }

  public boolean isElementPresent(WebElement componentEl, By locator) {
    try {
      return !componentEl.findElements(locator).isEmpty();
    } catch (Exception e) {
      log.atError().log("isElementPresent " + e.getMessage());
      return false;
    }
  }


  public List<WebElement> waitForElementsToBeVisible(By locator) {
    try {
      return waitUtils.explicitWait()
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    } catch (Exception e) {
      log.atError().log("waitForElementsToBeVisible " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public List<WebElement> waitForElementsToBeVisible(By parent, By locator) {
    try {
      return waitUtils.explicitWait()
        .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, locator));
    } catch (Exception e) {
      log.atError().log("waitForElementsToBeVisible " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public List<WebElement> waitForElementsToBeVisible(By parent, By locator, long timeInMills) {
    try {
      return waitUtils.createWebDriverWait(timeInMills)
        .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, locator));
    } catch (Exception e) {
      log.atError().log("waitForElementsToBeVisible " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public WebElement waitForFindingElement(By locator) {
    try {
      return waitForCondition(driver -> {
        if (driver != null) {
          return driver.findElement(locator);
        }
        return null;
      });
    } catch (Exception e) {
      setLogException("waitForFindingElement ", e);
      throw new RuntimeException(e);
    }
  }

  public WebElement waitForFindingElement(WebElement componentEl, By childLocator) {
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

  public WebElement waitForFindingElement(WebElement componentEl, By childLocator, long timeoutInMills) {
    try {
      return waitForCondition(component -> {
        if (component != null) {
          return componentEl.findElement(childLocator);
        }
        return null;
      }, timeoutInMills);
    } catch (Exception e) {
      String errorMessage = String.format("Exception occurred while waiting for elements: %s", childLocator);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new RuntimeException(errorMessage, e);
    }
  }

  public WebElement waitForFindingElement(By locator, long timeoutInMills) {

    try {
      return this.waitForCondition(driver -> {
        assert driver != null;
        return driver.findElement(locator);
      }, timeoutInMills);
    } catch (WaitForConditionException e) {
      String errorMessage = String.format("Exception occurred while waiting for elements: %s", locator);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new WaitForElementException(errorMessage);
    }
  }

  public List<WebElement> waitForFindingElements(By locator) {

    try {
      return this.waitForCondition(driver -> {
        assert driver != null;
        return driver.findElements(locator);
      });
    } catch (WaitForConditionException e) {
      String errorMessage = String.format("Exception occurred while waiting for elements: %s", locator);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new WaitForConditionException(errorMessage + e.getMessage());
    }
  }


  public List<WebElement> waitForFindingElements(WebElement componentElement, By locator) {

    try {
      return this.waitForCondition(driver -> {
        assert driver != null;
        return componentElement.findElements(locator);
      });
    } catch (WaitForConditionException e) {
      String errorMessage = String.format("Exception occurred while waiting for elements: %s", locator);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new WaitForConditionException(errorMessage + e.getMessage());
    }
  }

  public List<WebElement> waitForFindingElements(By locator, long timeoutInMills) {

    try {
      return this.waitForCondition(driver -> {
        assert driver != null;
        return driver.findElements(locator);
      }, timeoutInMills);
    } catch (WaitForConditionException e) {
      log.atError().log("waitForFindingElements " + e.getMessage());
      throw e;
    }
  }


//  public WebElement waitForElementToBeVisible(By locator) {
//    try {
//      return waitUtils.explicitWait()
//        .until(ExpectedConditions.visibilityOfElementLocated(locator));
//    } catch (TimeoutException e) {
//      String message = String.format("Timeout while waiting for element to be visible: %s", locator);
//      log.atError().log(message);
//      throw new TimeoutException(message, e);
//
//    } catch (NoSuchElementException e) {
//      String message = String.format("Element not found while waiting for it to be visible: %s", locator);
//      log.atError().log(message);
//      throw new NoSuchElementException(message, e);
//    } catch (StaleElementReferenceException e) {
//      String message = String.format("Element became stale while waiting for it to be visible: %s", locator);
//      log.atError().log(message);
//      throw new StaleElementReferenceException(message, e);
//    } catch (Exception e) {
//      String message = String.format("An unexpected error occurred while waiting for element to be visible: %s", locator);
//      log.atError().log(message);
//      throw new RuntimeException(message, e);
//    }
//  }

  public WebElement waitForElementToBeVisible(By locator) {
    try {
      return waitUtils.explicitWait()
        .until(ExpectedConditions.visibilityOfElementLocated(locator));

    } catch (Exception e) {
      String message = String.format("An unexpected error occurred while waiting for element to be visible: %s", locator);
      log.atError().log(message);
      return null;
    }
  }

  public WebElement waitForElementToBeVisible(WebElement componentEl, By locator, long timeInMillis) {
    try {
      return waitUtils.createWebDriverWait(timeInMillis)
        .until(CustomExpectedConditions.visibilityOfElementLocated(componentEl, locator));
    } catch (TimeoutException e) {
      String message = String.format("Timeout while waiting for element to be visible: %s", locator);
      log.atError().log(message);
      throw new TimeoutException(message, e);

    } catch (NoSuchElementException e) {
      String message = String.format("Element not found while waiting for it to be visible: %s", locator);
      log.atError().log(message);
      throw new NoSuchElementException(message, e);
    } catch (StaleElementReferenceException e) {
      String message = String.format("Element became stale while waiting for it to be visible: %s", locator);
      log.atError().log(message);
      throw new StaleElementReferenceException(message, e);
    } catch (Exception e) {
      String message = String.format("An unexpected error occurred while waiting for element to be visible: %s", locator);
      log.atError().log(message);
      throw new RuntimeException(message, e);
    }
  }

  public WebElement waitForElementToBeVisible(WebElement componentEl, By locator) {
    try {
      return waitUtils.explicitWait()
        .until(CustomExpectedConditions.visibilityOfElementLocated(componentEl, locator));
    } catch (TimeoutException e) {
      String message = String.format("Timeout while waiting for element to be visible: %s", locator);
      log.atError().log(message);
      throw new TimeoutException(message, e);

    } catch (NoSuchElementException e) {
      String message = String.format("Element not found while waiting for it to be visible: %s", locator);
      log.atError().log(message);
      throw new NoSuchElementException(message, e);
    } catch (StaleElementReferenceException e) {
      String message = String.format("Element became stale while waiting for it to be visible: %s", locator);
      log.atError().log(message);
      throw new StaleElementReferenceException(message, e);
    } catch (Exception e) {
      String message = String.format("An unexpected error occurred while waiting for element to be visible: %s", locator);
      log.atError().log(message);
      throw new RuntimeException(message, e);
    }
  }

  public WebElement waitForElementToBeVisible(By locator, long timeInMillis) {

    try {
      return waitUtils.createWebDriverWait(timeInMillis)
        .until(ExpectedConditions.visibilityOfElementLocated(locator));
    } catch (TimeoutException e) {
      String message = String.format("Timeout after %d milliseconds waiting for element to be visible: %s", timeInMillis, locator);
      log.atError().log(message);
//      throw new TimeoutException(message, e);
      return null;
    } catch (Exception e) {
      String message = String.format("An unexpected error occurred while waiting for element to be visible: %s", locator);
      log.atError().log(message);
      //throw new RuntimeException(message, e);
      return null;
    }

  }

  public WebElement waitForElementToBeVisible(WebElement element) {
    try {
      return waitUtils.explicitWait()
        .until(ExpectedConditions.visibilityOf(element));
    } catch (TimeoutException e) {
      String message = String.format("Timeout while waiting for element to be visible: %s", element);
      log.atError().log(message);
      throw new TimeoutException(message, e);
    } catch (StaleElementReferenceException e) {
      String message = String.format("Element became stale while waiting for it to be visible: %s", element);
      log.atError().log(message);
      throw new StaleElementReferenceException(message, e);
    } catch (Exception e) {
      String message = String.format("An unexpected error occurred while waiting for element to be visible: %s", element);
      log.atError().log(message);
      throw new RuntimeException(message, e);
    }
  }

  public WebElement waitForElementToBeVisible(WebElement element, long timeInMillis) {
    try {
      return waitUtils.createWebDriverWait(timeInMillis)
        .until(ExpectedConditions.visibilityOf(element));
    } catch (TimeoutException e) {
      String message = String.format("Timeout after %d milliseconds waiting for element to be visible: %s", timeInMillis, element);
      log.atError().log(message);
      throw new TimeoutException(message, e);
    } catch (StaleElementReferenceException e) {
      String message = String.format("Element became stale while waiting for it to be visible: %s", element);
      log.atError().log(message);
      throw new StaleElementReferenceException(message, e);
    } catch (Exception e) {
      String message = String.format("An unexpected error occurred while waiting for element to be visible: %s", element);
      log.atError().log(message);
      throw new RuntimeException(message, e);
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
      setLogException(errorMessage, e);
      throw new WaitForElementException(errorMessage, e);
    }

  }

  public WebElement waitForElementTobeClickable(By locator, long timeInMillis) {
    try {
      WebElement element = waitUtils.createWebDriverWait(timeInMillis)
        .until(ExpectedConditions.elementToBeClickable(locator));

      if (element == null) {
        String errorMessage = String.format("Element not found while waiting to be clickable: %s", locator);
        log.atError().setMessage(errorMessage).log();
        throw new WaitForElementException(errorMessage);
      }

      return element;

    } catch (Exception e) {
      String errorMessage = String.format("Unexpected exception while waiting for element to be clickable: %s", locator);
      setLogException(errorMessage, e);
      throw new WaitForElementException(errorMessage, e);
    }
  }

  public void waitForElementTobeClickable(WebElement element) {

    try {
      waitUtils.explicitWait()
        .until(ExpectedConditions.elementToBeClickable(element));
    } catch (TimeoutException e) {
      String errorMessage = String.format("Timeout waiting for element to be clickable: %s", element);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new WaitForElementException(errorMessage, e);
    } catch (ElementNotInteractableException e) {
      String errorMessage = String.format("Element not interactable while waiting to be clickable: %s", element);
      setLogException(errorMessage, e);
      throw new WaitForElementException(errorMessage, e);
    } catch (Exception e) {
      String errorMessage = String.format("Unexpected exception while waiting for element to be clickable: %s", element);
      setLogException(errorMessage, e);
      throw new WaitForElementException(errorMessage, e);
    }
  }

  public WebElement waitForElementTobeClickable(WebElement element, long timeInMillis) {
    try {
      return waitUtils.createWebDriverWait(timeInMillis)
        .until(ExpectedConditions.elementToBeClickable(element));
    } catch (TimeoutException e) {
      String errorMessage = String.format("Timeout waiting for element to be clickable with timeout %d ms: %s", timeInMillis, element);
      setLogException(errorMessage, e);
      throw new WaitForElementException(errorMessage, e);
    } catch (NoSuchElementException e) {
      String errorMessage =
        String.format("Element not found while waiting to be clickable with timeout %d ms: %s", timeInMillis, element);
      setLogException(errorMessage, e);
      throw new WaitForElementException(errorMessage, e);
    } catch (StaleElementReferenceException e) {
      String errorMessage =
        String.format("Stale element reference while waiting to be clickable with timeout %d ms: %s", timeInMillis, element);
      throw new WaitForElementException(errorMessage, e);
    } catch (ElementNotInteractableException e) {
      String errorMessage =
        String.format("Element not interactable while waiting to be clickable with timeout %d ms: %s", timeInMillis, element);
      throw new WaitForElementException(errorMessage, e);
    } catch (WebDriverException e) {
      String errorMessage =
        String.format("WebDriverException while waiting for element to be clickable with timeout %d ms: %s", timeInMillis, element);
      throw new WaitForElementException(errorMessage, e);
    } catch (Exception e) {
      String errorMessage =
        String.format("Unexpected exception while waiting for element to be clickable with timeout %d ms: %s", timeInMillis, element);
      throw new WaitForElementException(errorMessage, e);
    }
  }

  public WebElement findElement(Map<PlatformType, By> locatorMap) {

    By locator = locatorMap.get(currentPlatform);

    return driver.findElement(locator);
  }

  public List<WebElement> findElements(Map<PlatformType, By> locatorMap) {

    By locator = locatorMap.get(currentPlatform);

    return driver.findElements(locator);
  }

  public List<WebElement> findElementsByIdPrefix(String idPrefix) {
    By locator = AppiumBy.androidUIAutomator(
      "new UiSelector().resourceIdMatches(\"^" + idPrefix + ".*\")");

    List<WebElement> elements = driver.findElements(locator);

    for (WebElement element : elements) {
      System.out.println("Found element with ID: " + element.getAttribute("resource-id"));
    }

    return elements;
  }


  public Alert waitForAlertIsPresent(WebDriver driver, long timeoutInMillis) {
    try {
      return waitUtils.createWebDriverWait(timeoutInMillis)
        .until(ExpectedConditions.alertIsPresent());
    } catch (Exception e) {
      log.atError().setMessage("Error while waiting for alert to be present").setCause(e).log();
      return null;
    }
  }

  public Boolean isScreenTitleDisplayedCorrect(String expectedTitle) {
    try {
      return waitUtils.explicitWait()
        .until(ExpectedConditions.titleIs(expectedTitle));

    } catch (Exception e) {
      log.atInfo().setMessage("Error while waiting for screen title to be displayed").setCause(e).log();
      return false;
    }
  }

  public Boolean isElementPresentText(By locator, String text) {
    try {
      return waitUtils.explicitWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

    } catch (Exception e) {
      log.atInfo().setMessage("Element Not Displayed and does not have text").setCause(e).log();
      return false;
    }
  }

  public Boolean isElementPresentText(By locator, String text, long timeoutInMillis) {
    try {
      return waitUtils.createWebDriverWait(timeoutInMillis)
        .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

    } catch (Exception e) {
      log.atInfo().setMessage("Element Not Displayed and does not have text").setCause(e).log();
      return false;
    }
  }

  public Boolean isElementPresentText(WebElement element, String text) {
    try {
      return waitUtils.explicitWait()
        .until(ExpectedConditions.textToBePresentInElement(element, text));

    } catch (Exception e) {
      log.atInfo().setMessage("Element Not Displayed and does not have text").setCause(e).log();
      return false;
    }
  }

  public Boolean isElementPresentText(WebElement element, String text, long timeoutInMillis) {
    try {
      return waitUtils.createWebDriverWait(timeoutInMillis)
        .until(ExpectedConditions.textToBePresentInElement(element, text));
    } catch (Exception e) {
      log.atInfo().setMessage("Element Not Displayed and does not have text").setCause(e).log();
      return false;
    }
  }


  public boolean isTextDisplayedCorrect(WebElement element, String title) {
    return element.isDisplayed() && element.getText().equalsIgnoreCase(title);
  }

  public boolean waitForTextPresentInElement(By locator, String text) {
    return waitUtils.explicitWait()
      .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
  }


  //TODO : function interface
  private <T> T waitForCondition(ExpectedCondition<T> condition) {
    try {
      return waitUtils.explicitWait()
        .until(condition);
    } catch (Exception e) {
      String errorMessage = String.format("Timeout waiting for condition: %s", condition.toString());
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new WaitForConditionException(errorMessage, e);
    }
  }


  private <T> T waitForCondition(ExpectedCondition<T> condition, long timeoutInMillis) {
    try {
      return waitUtils.createWebDriverWait(timeoutInMillis)
        .until(condition);
    } catch (TimeoutException e) {
      String errorMessage = String.format("Timeout while waiting for condition: %s", condition);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new WaitForConditionException(errorMessage, e);

    } catch (Exception e) {
      String errorMessage = String.format("Unexpected exception while waiting for condition: %s", condition);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new WaitForConditionException(errorMessage, e);
    }
  }


  public WebElement getElementFromText(AppiumDriver driver, List<WebElement> elementList, String textOfElement) {
    for (WebElement element : elementList) {
      if (textOfElement.equals(element.getText())) {
        return element;
      }
    }
    return null;
  }


  public Boolean waitForElementsToBeInvisible(By locator) {
    try {
      return waitUtils.explicitWait()
        .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    } catch (Exception e) {
      log.atInfo().setMessage("Element Not Displayed and does not have text").setCause(e).log();
      return false;
    }
  }

  public List<WebElement> waitForNestedElementsToBePresence(By parent, By locator) {
    try {
      return waitUtils.explicitWait().until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parent, locator));
    } catch (Exception e) {
      log.atInfo().setMessage("Element Not Displayed and does not have text").setCause(e).log();
      return null;
    }
  }

  public void inputText(WebElement element, String text) {
    element.clear();
    element.sendKeys(text);
  }

  private static void setLogException(String errorMessage, Exception e) {
    log.atError().setMessage(errorMessage).setCause(e).log();
  }
}

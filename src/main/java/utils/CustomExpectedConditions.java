package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {
  private CustomExpectedConditions() {
  }


  public static ExpectedCondition<WebElement> presenceOfElementLocated(WebElement componentEl, final By locator) {
    return new ExpectedCondition<WebElement>() {
      public WebElement apply(WebDriver driver) {
        return componentEl.findElement(locator);
      }

      public String toString() {
        return "presence of element located by: " + locator;
      }
    };
  }

  public static ExpectedCondition<WebElement> visibilityOfElementLocated(WebElement componentEl, final By locator) {
    return new ExpectedCondition<WebElement>() {
      public WebElement apply(WebDriver driver) {
        try {
          return CustomExpectedConditions.elementIfVisible(componentEl.findElement(locator));
        } catch (NoSuchElementException | StaleElementReferenceException var3) {
          return null;
        }
      }

      public String toString() {
        return "visibility of element located by " + locator;
      }
    };
  }

  private static WebElement elementIfVisible(WebElement element) {
    return element.isDisplayed() ? element : null;
  }

}

package learning.screens.commponents.webView;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.ElementUtils;

public class TopNavBarComponent {
  private final AppiumDriver driver;
  private final ElementUtils elementUtils;
  private final By navBarFixedTop = By.cssSelector(".navbar navbar--fixed-top");
  private final By navToggleBarLoc = By.cssSelector(".navbar__toggle");
  private final By closeBtnLoc = By.cssSelector(".navbar-sidebar__close");


  public TopNavBarComponent(AppiumDriver driver) {
    this.driver = driver;
    this.elementUtils = new ElementUtils(driver);
  }

  protected void ensureTopNavLoaded(By locator) {
    // Waits for the element to be visible and throws an exception if it is not
    WebElement element = elementUtils.waitForElementToBeVisible(locator);

    if (element != null) {
      System.out.println("Top nav is loaded. Element found: " + locator.toString());
    } else {
      throw new NoSuchElementException("Top nav is not loaded. Element not found: " + locator.toString());
    }
  }

  public WebElement navBarFixedTopEl() {
    return elementUtils.waitForElementToBeVisible(navBarFixedTop);
  }

  public WebElement navToggleBarEl() {
    return elementUtils.waitForElementToBeVisible(navToggleBarLoc);
  }

  public WebElement closeBtnEl() {
    return elementUtils.waitForElementTobeClickable(closeBtnLoc);
  }

  public void clickOnLeftNavToggle() {
    navToggleBarEl().click();
  }

  public void clickOnCloseBtn() {
    closeBtnEl().click();
  }
}

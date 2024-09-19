package learning.screens.commponents.webView;

import entity.webView.MenuItemDataModel;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ElementUtils;

import java.util.ArrayList;
import java.util.List;

public class LeftNavBarComponent {
  private final AppiumDriver driver;
  private final ElementUtils elementUtils;
  private final By menuListLoc = By.cssSelector(".menu__list");
  private final By menuItemLoc = By.cssSelector(".menu__list-item a");

  public LeftNavBarComponent(AppiumDriver driver) {

    this.driver = driver;
    this.elementUtils = new ElementUtils(this.driver);
  }

  protected void ensureLeftNavLoaded(By locator) {
    // Waits for the element to be visible and throws an exception if it is not
    WebElement element = elementUtils.waitForElementToBeVisible(locator);

    if (element != null) {
      System.out.println("Top nav is loaded. Element found: " + locator.toString());
    } else {
      throw new NoSuchElementException("Top nav is not loaded. Element not found: " + locator.toString());
    }
  }

  public List<WebElement> menuListEl() {
    return driver.findElements(menuListLoc);
  }

  public List<WebElement> menuItemEls() {
    List<WebElement> menuItems = driver.findElements(menuItemLoc);
    Assert.assertNotNull(menuItems, "webView is null");

    return menuItems;

  }


  public List<MenuItemDataModel> getMainMenuItems() {
    List<WebElement> menuItems = menuItemEls();
    checkListNotEmpty(menuItems);

    List<MenuItemDataModel> actualMainMenuItems = new ArrayList<>();
    for (WebElement menuItem : menuItems) {
      String text = menuItem.getText();
      String href = menuItem.getAttribute("href");
      String ariaLabel = menuItem.getAttribute("aria-label");

      actualMainMenuItems.add(new MenuItemDataModel(text, href, ariaLabel));
    }

    return actualMainMenuItems;
  }


  public List<MenuItemDataModel> getMenuItemList() {
    List<WebElement> menuItems = menuItemEls();
    checkListNotEmpty(menuItems);

    List<MenuItemDataModel> menuItemList = new ArrayList<>();
    for (WebElement menuItem : menuItems) {
      String text = menuItem.getText();
      String href = menuItem.getAttribute("href");
      String ariaLabel = menuItem.getAttribute("aria-label");

      menuItemList.add(new MenuItemDataModel(text, href, ariaLabel));
    }

    return menuItemList;
  }


  private static void checkListNotEmpty(List<WebElement> elements) {
    if (elements.isEmpty()) {
      throw new IllegalArgumentException("The menuListEl is empty");
    }
  }

}

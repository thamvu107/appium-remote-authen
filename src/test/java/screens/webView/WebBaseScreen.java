package screens.webView;

import base.BaseScreen;
import entity.webView.MenuItemDataModel;
import io.appium.java_client.AppiumDriver;
import learning.screens.commponents.webView.LeftNavBarComponent;
import learning.screens.commponents.webView.TopNavBarComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;

public class WebBaseScreen extends BaseScreen {
  private final AppiumDriver driver;
  // protected ContextSwitching contextSwitching;
  public TopNavBarComponent topNavBarComponent;

  public LeftNavBarComponent leftNavBarComponent;
  private final By webViewScreenLoc = By.cssSelector("#__docusaurus");
  private final By navBarLoc = By.cssSelector(".navbar-sidebar");
  private final By announcementBarLoc = By.cssSelector(".announcementBar_mb4j");
  private final By announcementCloseBtnLoc = By.cssSelector("button[class*='closeButton']");

  public WebBaseScreen(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
    switchToWebViewContext();
    this.topNavBarComponent = new TopNavBarComponent(this.driver);
    this.leftNavBarComponent = new LeftNavBarComponent(this.driver);
  }

//    public WebDriver switchToNativeContext() {
//
//        return contextSwitching.switchToNativeContext(LONG_EXPLICIT_WAIT);
//    }
//
//    public WebDriver switchToWebViewContext() {
//
//        return contextSwitching.switchToWebViewContext(LONG_EXPLICIT_WAIT);
//    }


  protected void verifyScreenTitle(String expectedTitle) {
    String actualTitle = driver.getTitle();
    if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
      throw new IllegalStateException("This is not the expected screen: " + expectedTitle + ". Current screen: " + actualTitle);
    }
  }

  public boolean showingNavBar() {
    return elementUtils.isElementPresent(navBarLoc);
  }

  public WebElement announcementCloseBtnEl() {
    return elementUtils.waitForElementTobeClickable(announcementCloseBtnLoc);
  }


  public WebBaseScreen closeAnnouncement() {
    if (elementUtils.isElementPresent(announcementBarLoc)) {
      announcementCloseBtnEl().click();
    }

    return this;
  }

  public WebBaseScreen openLeftNav() {
    topNavBarComponent.clickOnLeftNavToggle();

    return this;
  }

  public WebBaseScreen closeLeftNav() {
    if (showingNavBar()) {
      topNavBarComponent.clickOnCloseBtn();
    }

    return this;
  }


  public boolean isAnnouncementBarInvisible() {
    return elementUtils.waitForElementsToBeInvisible(announcementBarLoc);
  }

  public boolean hasMenuItem(MenuItemDataModel expectedMenuItem) {

    List<MenuItemDataModel> actualMainMenuItems = leftNavBarComponent.getMainMenuItems();

    return actualMainMenuItems.contains(expectedMenuItem);
  }

  public boolean isMenuItemListCorrect(List<MenuItemDataModel> expectedMenuItems) {

    List<MenuItemDataModel> actualMenuItems = leftNavBarComponent.getMenuItemList();

    return new HashSet<>(actualMenuItems).containsAll(expectedMenuItems);
  }

}

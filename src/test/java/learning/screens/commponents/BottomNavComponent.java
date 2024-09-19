package learning.screens.commponents;

import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import screens.HomeScreen;
import screens.SwipeScreen;
import screens.login.LoginScreen;
import screens.webView.WebHomeScreen;
import utils.ElementUtils;
import utils.InteractionUtils;
import utils.PlatformUtil;

import static io.appium.java_client.AppiumBy.accessibilityId;

public class BottomNavComponent {
  private final AppiumDriver driver;
  private final static By homeNavLoc = accessibilityId("Home");
  private final static By webViewNavLoc = accessibilityId("Webview");
  private final static By loginNavLoc = accessibilityId("Login");
  private final static By formsNavLoc = accessibilityId("Forms");
  private final static By swipeNavLoc = accessibilityId("Swipe");

  private final PlatformType currentPlatform;
  //
  private final ElementUtils elementUtils;

  private final InteractionUtils mobileInteractions;


  public BottomNavComponent(AppiumDriver driver) {

    this.driver = driver;
    this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
    this.elementUtils = new ElementUtils(this.driver);
    this.mobileInteractions = new InteractionUtils(this.driver);
    //ensureBottomNavLoaded(homeNavLoc);

  }

  protected void ensureBottomNavLoaded(By locator) {
    // Waits for the element to be visible and throws an exception if it is not
    WebElement element = elementUtils.waitForElementToBeVisible(locator);

    if (element == null) {
      throw new IllegalStateException("Bottom nav is not loaded");
    }
  }

  public WebElement homeNavEl() {

    return elementUtils.waitForFindingElement(homeNavLoc);
  }

  private WebElement homeNavEl(long durationInMillis) {
    return elementUtils.waitForElementTobeClickable(homeNavLoc, durationInMillis);
  }

  public WebElement webNavEl() {

    return elementUtils.waitForElementTobeClickable(webViewNavLoc);
  }

  public WebElement loginNavEl() {

//    return elementUtils.waitForElementTobeClickable(loginNavLoc);
//    return elementUtils.waitForFindingElement(loginNavLoc, 7000);
    return driver.findElement(loginNavLoc);
  }

  private WebElement formsNavEl() {

    return elementUtils.waitForElementTobeClickable(formsNavLoc);
  }

  private WebElement swipeNavEl() {

    return elementUtils.waitForElementTobeClickable(swipeNavLoc);
  }

  private WebElement webViewNavEl() {

    return elementUtils.waitForElementTobeClickable(webViewNavLoc);
  }

  public LoginScreen openLoginScreen() {
    loginNavEl().click();

    return new LoginScreen(driver);
  }


  public HomeScreen clickOnHomeNav() {
    homeNavEl().click();

    return new HomeScreen(driver);
  }

  public SwipeScreen clickOnSwipeNav() {
    swipeNavEl().click();

    return new SwipeScreen(driver);
  }

  public WebHomeScreen clickOnWebViewNav() {
    webViewNavEl().click();

    return new WebHomeScreen(driver);
  }
}

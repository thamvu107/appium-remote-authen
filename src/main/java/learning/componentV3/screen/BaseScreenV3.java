package learning.componentV3.screen;

import context.ContextSwitching;
import enums.PlatformType;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import learning.componentV3.components.BottomNavComponent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import screens.login.LoginScreen;
import utils.ElementUtils;
import utils.PlatformUtil;

import java.util.Map;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.xpath;

@Slf4j
public class BaseScreenV3 {

  protected AppiumDriver driver;
  protected WebElement rootEl;
  protected ContextSwitching contextSwitching;


  @Getter
  protected BottomNavComponent bottomNavComponent;

  protected ElementUtils elementUtils;

  protected PlatformType currentPlatform;

  public BaseScreenV3(AppiumDriver driver) {
    // TODO: variant platform type

    validateDriverNotNull(driver);
    this.driver = driver;
    this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
    this.contextSwitching = new ContextSwitching(this.driver);
    this.elementUtils = new ElementUtils(driver);

//    this.bottomNavComponent = new BottomNavComponent(driver, bottomNavEl());
    this.bottomNavComponent =
      new BottomNavComponent(driver, new ElementUtils(driver).waitForFindingElement(bottomNavLocatorMap.get(currentPlatform)));
    System.out.println("this.bottomNavComponent " + this.bottomNavComponent);
  }

  private static final By androidRootLocator =
    xpath("//android.widget.LinearLayout[@resource-id=\"com.wdiodemoapp:id/action_bar_root\"]");
  private static final By iosRootLocator = accessibilityId("wdiodemoapp");

  private static final Map<PlatformType, By> rootLocator = Map.of(
    PlatformType.ANDROID, androidRootLocator,
    PlatformType.IOS, iosRootLocator);

  private final By webViewScreenLoc = By.cssSelector("#__docusaurus");

  private final By navBarLoc = By.cssSelector(".navbar-sidebar");
  private final By announcementBarLoc = By.cssSelector(".announcementBar_mb4j");
  private final By announcementCloseBtnLoc = By.cssSelector("button[class*='closeButton']");
//  private static final By androidBottomLocator = AppiumBy.xpath(
//    "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.View");

  private static final By androidBottomLocator = AppiumBy.className("android.view.View");
  private static final By iosBottomLocator =
    accessibilityId("(//XCUIElementTypeOther[@name=\"Home Webview Login Forms Swipe Drag\"])[1]");

  protected static final Map<PlatformType, By> bottomNavLocatorMap = Map.of(
    PlatformType.ANDROID, androidBottomLocator,
    PlatformType.IOS, iosBottomLocator);

  protected final WebElement bottomNavEl() {
    By bottomNavLocator = bottomNavLocatorMap.get(currentPlatform);

    return elementUtils.waitForFindingElement(bottomNavLocator);
  }

  private static void validateDriverNotNull(AppiumDriver driver) {
    if (driver == null) {
      log.atError().setMessage("Driver cannot be null").log();
      throw new IllegalArgumentException("Driver cannot be null");
    }
  }

  public String getCurrentContext() {
    return contextSwitching.getCurrentContext(driver);
  }

  public WebDriver switchToNativeContext() {
    return contextSwitching.switchToNativeContext(LONG_EXPLICIT_WAIT);
  }

  public WebDriver switchToWebViewContext() {
    return contextSwitching.switchToWebViewContext(SHORT_EXPLICIT_WAIT);

  }

  public HomeScreenV3 openHomeScreen() {
    return this.bottomNavComponent.clickOnHomeNav();
  }

  public LoginScreen openLoginScreen() {

    return this.bottomNavComponent.clickOnLoginNav();
  }

  public SwipeScreenV3 openSwipeScreen() {

    try {
      return this.bottomNavComponent.clickOnSwipeNav();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public boolean showingNavBar() {
    return elementUtils.isElementPresent(navBarLoc);
  }

  public WebElement announcementCloseBtnEl() {

    return elementUtils.waitForElementTobeClickable(announcementCloseBtnLoc);
  }

  public BaseScreenV3 closeAnnouncement() {
    if (elementUtils.isElementPresent(announcementBarLoc)) {
      announcementCloseBtnEl().click();
    }

    return this;
  }

  protected void verifyScreenLoaded(By locator) {
    // Waits for the element to be visible and throws an exception if it is not
//    WebElement element = componentElementUtils.waitForFindingElement(locator);
    WebElement element = null;
    try {
      element = elementUtils.waitForElementToBeVisible(locator);
      if (element == null) {
        String errorMessage =
          String.format("%s: Page is not loaded. Element: '%s' is not visible", getClass().getSimpleName(), locator);
        log.atError().setMessage(errorMessage).log();
        throw new NoSuchElementException(errorMessage);
      } else {
        String infoMessage = String.format("%s: Page is loaded. Element: '%s' is visible", getClass().getSimpleName(), locator);
        log.atInfo().setMessage(infoMessage).log();
      }
    } catch (Exception e) {
      String errorMessage = String.format("%s: Page is not loaded. Element: '%s' is not visible", getClass().getSimpleName(), locator);
      log.atError().setMessage(errorMessage).setCause(e).log();

      throw new RuntimeException(errorMessage, e);
    }
  }

  protected void verifyScreenLoaded(WebElement element) {
    try {
      if (element == null) {
        String errorMessage =
          String.format("%s: Page is not loaded. Element: '%s' is not visible", getClass().getSimpleName(), element);
        log.atError().setMessage(errorMessage).log();
        throw new NoSuchElementException(errorMessage);
      } else {
        String infoMessage = String.format("%s: Page is loaded. Element: '%s' is visible", getClass().getSimpleName(), element);
        log.atInfo().setMessage(infoMessage).log();
      }
    } catch (Exception e) {
      String errorMessage = String.format("%s: Page is not loaded. Element: '%s' is not visible", getClass().getSimpleName(), element);
      log.atError().setMessage(errorMessage).setCause(e).log();

      throw new RuntimeException(errorMessage, e);
    }
  }

}

package learning.componentExploring.screens;

import context.ContextSwitching;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import learning.componentExploring.components.BottomNavComponent;
import learning.componentExploring.components.Component;
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
import static io.appium.java_client.AppiumBy.id;

@Slf4j
public class BaseScreenV2 extends Component {

  protected AppiumDriver driver;
  protected WebElement rootEl;
  protected ContextSwitching contextSwitching;


  @Getter
  protected BottomNavComponent bottomNavComponent;

  protected ElementUtils elementUtils;

  protected PlatformType currentPlatform;

  public BaseScreenV2(AppiumDriver driver) {
    // TODO: variant platform type
//    super(driver, rootLocator);
//    super(driver, driver.findElement(androidRootLocator));
//    super(driver, new ElementUtils(driver).waitForFindingElement(androidRootLocator));
    super(driver, new ElementUtils(driver).waitForFindingElement(androidRootLocator, 25_000L));

    validateDriverNotNull(driver);
    this.driver = driver;
    this.rootEl = getComponentEl();
    this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
    this.contextSwitching = new ContextSwitching(this.driver);
    this.elementUtils = new ElementUtils(driver);
    this.bottomNavComponent = findComponent(BottomNavComponent.class);

//    this.bottomNavComponent = findComponent(ExploringBottomNavComponent.class); //getBottomNavComponent();
//    this.elementUtils = new ElementUtils(driver);

//    this.interactionUtils = new InteractionUtils(driver);
//        try {
//            this.bottomNavComponent = getBottomNavComponent();
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
  }


  //  private static final By androidRootLocator = id("com.wdiodemoapp:id/action_bar_root");
  private static final By androidRootLocator = id("com.wdiodemoapp:id/action_bar_root");
  private static final By iosRootLocator = accessibilityId("wdiodemoapp");

  private static final Map<PlatformType, By> rootLocator = Map.of(
    PlatformType.ANDROID, androidRootLocator,
    PlatformType.IOS, iosRootLocator);

  private final By webViewScreenLoc = By.cssSelector("#__docusaurus");
  private final By navBarLoc = By.cssSelector(".navbar-sidebar");
  private final By announcementBarLoc = By.cssSelector(".announcementBar_mb4j");
  private final By announcementCloseBtnLoc = By.cssSelector("button[class*='closeButton']");
  private static final By androidBottomLocator = id(
    "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.View");
  private static final By iosBottomLocator =
    accessibilityId("(//XCUIElementTypeOther[@name=\"Home Webview Login Forms Swipe Drag\"])[1]");

  protected static final Map<PlatformType, By> bottomNavLocator = Map.of(
    PlatformType.ANDROID, androidRootLocator,
    PlatformType.IOS, iosRootLocator);

  //protected AssertUtils assertUtils;

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

  public HomeScreenV2 openHomeScreen() {
    return this.bottomNavComponent.clickOnHomeNav();
  }

  public LoginScreen openLoginScreen() {

    return this.bottomNavComponent.clickOnLoginNav();
  }

  public SwipeScreenV2 openSwipeScreen() {

    try {
      return this.bottomNavComponent.clickOnSwipeNav();
//      return findComponent(ExploringBottomNavComponent.class).clickOnSwipeNav();
//      return getBottomNavComponent().clickOnWebViewNavV2();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public WebHomeScreenV2 openWebViewScreen() {

    try {
//      return this.bottomNavComponent.clickOnWebViewNavV2();
      return this.bottomNavComponent.clickOnWebViewNavV2();
//      return getBottomNavComponent().clickOnWebViewNavV2();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public boolean showingNavBar() {

//    return elementUtils.isElementPresent(navBarLoc);
//    return componentElementUtils.isElementPresent(navBarLoc);
    return elementUtils.isElementPresent(navBarLoc);
  }

  public WebElement announcementCloseBtnEl() {

    return elementUtils.waitForElementTobeClickable(announcementCloseBtnLoc);
  }

  public BaseScreenV2 closeAnnouncement() {
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

package screens.webView;

import context.ContextSwitching;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import learning.componentExploring.components.BottomNavComponent;
import learning.componentExploring.screens.HomeScreenV2;
import learning.componentExploring.screens.SwipeScreenV2;
import screens.login.LoginScreen;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementUtils;
import utils.PlatformUtil;

import java.util.Map;

import static constants.WaitConstants.LONG_EXPLICIT_WAIT;
import static constants.WaitConstants.SHORT_EXPLICIT_WAIT;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

@Slf4j
public class WebBaseScreenV3 {

  protected AppiumDriver driver;
  protected ContextSwitching contextSwitching;


  @Getter
  protected BottomNavComponent bottomNavComponent;

  protected ElementUtils elementUtils;

  //  protected InteractionUtils interactionUtils;
  protected PlatformType currentPlatform;

  private static final By androidRootLocator = id("com.wdiodemoapp:id/action_bar_root");
  private static final By iosRootLocator = accessibilityId("wdiodemoapp");

  private static final By androidBottomLocator = id(
    "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.View");
  private static final By iosBottomLocator =
    accessibilityId("(//XCUIElementTypeOther[@name=\"Home Webview Login Forms Swipe Drag\"])[1]");

  private static final Map<PlatformType, By> rootLocator = Map.of(
    PlatformType.ANDROID, androidRootLocator,
    PlatformType.IOS, iosRootLocator);


  private static final Map<PlatformType, By> bottomNavLocator = Map.of(
    PlatformType.ANDROID, androidRootLocator,
    PlatformType.IOS, iosRootLocator);

  //protected AssertUtils assertUtils;
  public WebBaseScreenV3(AppiumDriver driver) {
//    super(driver, rootLocator);

    validateDriverNotNull(driver);

    this.driver = driver;
    this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
    this.contextSwitching = new ContextSwitching(this.driver);
  }

  public BottomNavComponent getBottomNavComponent() {
    try {
//      this.bottomNavComponent = findComponent(BottomNavComponent.class);
//      this.bottomNavComponent = new BottomNavComponent(driver, bottomNavLocator);

      return this.bottomNavComponent;
    } catch (Exception e) {
      log.atError().setMessage("The componentExploreing must be defined with a selector").setCause(e).log();
      throw new RuntimeException("The componentExploreing MUST have a selector", e);
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
    return bottomNavComponent.clickOnHomeNav();
  }


  public LoginScreen openLoginScreen() {

    return bottomNavComponent.clickOnLoginNav();
  }

  public SwipeScreenV2 goToSwipeScreen() {
    return bottomNavComponent.clickOnSwipeNav();
  }

  public WebHomeScreen openWebViewScreen() {

    return bottomNavComponent.clickOnWebViewNav();
  }

  protected void verifyScreenLoaded(By locator) {
    // Waits for the element to be visible and throws an exception if it is not
    WebElement element = elementUtils.waitForElementToBeVisible(locator);
    log.info("Page is loaded. Element found: " + locator.toString());

    if (element == null) {
      log.atError().setMessage("Page is not loaded. Element not found: " + locator).log();
      throw new NoSuchElementException("Page is not loaded. Element not found: " + locator);
    }
  }

  private static void validateDriverNotNull(AppiumDriver driver) {
    if (driver == null) {
      log.atError().setMessage("Driver cannot be null").log();
      throw new IllegalArgumentException("Driver cannot be null");
    }
  }
}

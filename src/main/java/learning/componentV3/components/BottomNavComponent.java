package learning.componentV3.components;

import annotations.selectors.ComponentByXpath;
import io.appium.java_client.AppiumDriver;
import learning.componentV3.screen.HomeScreenV3;
import learning.componentV3.screen.SwipeScreenV3;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import screens.login.LoginScreen;
import screens.webView.WebHomeScreen;

import static io.appium.java_client.AppiumBy.accessibilityId;

// TODO: Explore Different Strategy Component Locators by platform types (Map?)
//@ComponentXpathSelector(value = "//android.view.ViewGroup/android.view.ViewGroup[2]/android.view.View")
@ComponentByXpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.View")
public class BottomNavComponent extends Component {
  private final AppiumDriver driver;
  @Getter
  private final WebElement componentEl;

//  public BottomNavComponent(AppiumDriver driver, Map<PlatformType, By> componentLocator) {
//    super(driver, componentLocator);
//    this.driver = driver;
//  }

//  public BottomNavComponent(AppiumDriver driver, By componentLocator) {
//    super(driver, componentLocator);
//    this.driver = driver;
//  }

  public BottomNavComponent(AppiumDriver driver, WebElement componentElement) {
    super(driver, componentElement);
    this.driver = driver;
    this.componentEl = componentElement;
  }

  private final By homeNavLoc = accessibilityId("Home");
  private final By webViewNavLoc = accessibilityId("Webview");
  private final By loginNavLoc = accessibilityId("Login");
  private final By formsNavLoc = accessibilityId("Forms");
  private final By swipeNavLoc = accessibilityId("Swipe");

  private WebElement homeNavEl() {

    return elementUtils.waitForFindingElement(componentEl, homeNavLoc);
  }

  private WebElement loginNavEl() {

    return elementUtils.waitForFindingElement(componentEl, loginNavLoc);
  }

  private WebElement formsNavEl() {

    return elementUtils.waitForFindingElement(componentEl, formsNavLoc);
  }

  private WebElement swipeNavEl() {

    return elementUtils.waitForFindingElement(componentEl, swipeNavLoc);
  }

  private WebElement webViewNavEl() {

    return elementUtils.waitForFindingElement(componentEl, webViewNavLoc);

  }

  public LoginScreen clickOnLoginNav() {
    loginNavEl().click();

    return new LoginScreen(driver);
  }

  public HomeScreenV3 clickOnHomeNav() {
    homeNavEl().click();

    return new HomeScreenV3(driver);
  }

  public SwipeScreenV3 clickOnSwipeNav() {
    swipeNavEl().click();

    return new SwipeScreenV3(driver);
  }

  public WebHomeScreen clickOnWebViewNav() {
    webViewNavEl().click();

    return new WebHomeScreen(driver);
  }

}

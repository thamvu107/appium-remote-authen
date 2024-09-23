package screens.login;

import base.BaseScreen;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import screens.alert.SignInAlertScreen;
import screens.alert.SignUpAlertScreen;
import utils.AlertHelper;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.xpath;

// MAIN INTERACTION METHODS
@Slf4j
public class LoginScreen extends BaseScreen {
  // TODO: Separate android locator and ios locator to 2 files.
  protected final AlertHelper alertHelper;

  public LoginScreen(final AppiumDriver driver) {

    super(driver);
    alertHelper = new AlertHelper(driver);
    verifyScreenLoaded(loginScreenLoc);
  }

  // Scope 01: Keep the selector
  // Android:
  protected final By loginScreenLoc = accessibilityId("Login-screen"); // android + ios
  protected final By loginTabLoc = accessibilityId("button-login-container"); // android + ios
  protected final By signInTabLoc = accessibilityId("button-login-container"); // android + ios
  protected final By signUpTabLoc = accessibilityId("button-sign-up-container"); // android + ios
  protected final By emailInputLoc = accessibilityId("input-email");
  protected final By passwordInputLoc = accessibilityId("input-password");
  protected final By androidInvalidEmailLabelLocator = xpath("//android.widget.TextView[@text='Please enter a valid email address']");
  protected final By androidInvalidPasswordLabelLocator =
    xpath("//android.widget.TextView[@text=\"Please enter at least 8 characters\"]");

  // IOS:
  protected final By iosInvalidEmailLabelLocator = accessibilityId("Please enter a valid email address");
  protected final By iosInvalidPasswordLabelLocator = accessibilityId("Please enter at least 8 characters");

  // Mapping
  private final Map<PlatformType, By> invalidEmailLabelLocatorMap = Map.of(
    PlatformType.ANDROID, androidInvalidEmailLabelLocator,
    PlatformType.IOS, iosInvalidEmailLabelLocator);
  private final Map<PlatformType, By> invalidPasswordLabelLocatorMap = Map.of(
    PlatformType.ANDROID, androidInvalidPasswordLabelLocator,
    PlatformType.IOS, iosInvalidPasswordLabelLocator);


  protected By invalidEmailLabelLoc = elementUtils.getLocator(invalidEmailLabelLocatorMap);
  protected By invalidPasswordLabelLoc = elementUtils.getLocator(invalidPasswordLabelLocatorMap);

  protected WebElement loginScreenElement() {

    return driver.findElement(loginScreenLoc);
  }

  protected WebElement loginTabElement() {

    return elementUtils.waitForElementTobeClickable(loginTabLoc);
  }

  protected WebElement signInTabEl() {

    return elementUtils.waitForElementTobeClickable(signInTabLoc);
  }

  protected WebElement signUpTabEl() {

    return elementUtils.waitForElementTobeClickable(signUpTabLoc);
  }

  protected WebElement emailFieldEl() {

    return elementUtils.waitForElementTobeClickable(emailInputLoc);
  }

  protected WebElement passwordFieldEl() {

    return elementUtils.waitForElementTobeClickable(passwordInputLoc);
  }

  protected WebElement invalidEmailLabelEl() {

    return elementUtils.waitForElementTobeClickable(invalidEmailLabelLoc);
  }

  protected WebElement invalidPasswordLabelEl() {

    return elementUtils.waitForFindingElement(invalidPasswordLabelLoc);
  }

  public void verifyScreenLoaded() {
    verifyScreenLoaded(loginScreenLoc);
  }

  public SignInScreen openSignInForm() {

    signInTabEl().click();

    return new SignInScreen(driver);
  }

  public SignUpScreen openSignUpForm() {
    signUpTabEl().click();

    return new SignUpScreen(driver);
  }

  public LoginScreen inputEmail(String email) {
    elementUtils.inputText(emailFieldEl(), email);

    return this;
  }

  public LoginScreen inputPassword(String password) {
    elementUtils.inputText(passwordFieldEl(), password);

    return this;
  }

  public SignUpAlertScreen switchToSignUpAlert() {

    return new SignUpAlertScreen(driver);
  }


  public SignInAlertScreen switchToSignInAlert() {

    return new SignInAlertScreen(driver);
  }

  public String getInvalidEmailMessage() {
    return invalidEmailLabelEl().getText();
  }

  public String getInvalidPasswordMessage() {

    return invalidPasswordLabelEl().getText();
  }

  public void closeAlert() {
    alertHelper.closeAlertIfPresent();
  }
}

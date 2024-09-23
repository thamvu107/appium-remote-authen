package screens.login;

import entity.authen.SignUpCred;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import screens.alert.SignUpAlertScreen;
import utils.gestures.swipe.vertical.SwipeVertically;

import java.util.Map;

import static constants.SwipeConstants.MOVE_DURATION;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.xpath;

@Slf4j
public class SignUpScreen extends LoginScreen {
//    private AppiumDriver driver;

  private final By repeatPasswordLocator = accessibilityId("input-repeat-password");
  private final By signUpButtonLocator = accessibilityId("button-SIGN UP");
  private final By androidInvalidRepeatPasswordLocator = xpath("//android.widget.TextView[@text=\"Please enter the same password\"]");
  private final By iosInvalidRepeatPasswordLocator = accessibilityId("Please enter the same password");

  public SignUpScreen(AppiumDriver driver) {

    super(driver);
    verifyScreenLoaded(signUpButtonLocator);
    log.atInfo().log("Sign up screen loaded");
  }

  private final Map<PlatformType, By> invalidRepeatPasswordLocatorMap = Map.of(
    PlatformType.ANDROID, androidInvalidRepeatPasswordLocator,
    PlatformType.IOS, iosInvalidRepeatPasswordLocator
  );

  private WebElement repeatPasswordElement() {
    log.atInfo().log("Get repeat password element");

    return driver.findElement(repeatPasswordLocator);
  }


  private WebElement invalidRepeatPasswordElement() {
    log.atInfo().log("Get invalid repeat password element");

    By locator = elementUtils.getLocator(invalidRepeatPasswordLocatorMap);

    return elementUtils.waitForFindingElement(locator);
  }


  private SignUpScreen inputRepeatPassword(String repeatPassword) {
    log.atInfo().log("Input repeat password");
    interactionUtils.setText(repeatPasswordElement(), repeatPassword);

    return this;
  }

  private void swipeUntilElementVisible(By locator) {
    while (!elementUtils.isElementDisplayed(locator)) {
      log.atInfo().log("Swipe until element visible");

      // TODO: swipe until see element
      SwipeVertically swipeVertically = new SwipeVertically(driver, 0.5f, 0.2f, 0.8f, MOVE_DURATION);
      swipeVertically.swipeUp();
    }
  }

  private void clickSignUpBtn() {
    log.atInfo().log("Submit sign up");
    swipeUntilElementVisible(signUpButtonLocator);

    WebElement signUpButtonElement = elementUtils.waitForElementTobeClickable(signUpButtonLocator);
    signUpButtonElement.click();
  }

  private SignUpAlertScreen submitSignUpSuccess() {
    log.atInfo().log("Sign up success");
    clickSignUpBtn();
    return new SignUpAlertScreen(driver);
  }

  private SignUpScreen submitSignUpFail() {
    log.atInfo().log("Sign up failed");
    clickSignUpBtn();
    return this;
  }

  public SignUpScreen signUpWithCred(SignUpCred signUpCred) {
    inputEmail(signUpCred.getEmail());
    inputPassword(signUpCred.getPassword());
    inputRepeatPassword(signUpCred.getRepeatPassword());
    clickSignUpBtn();

    return this;
  }

  public SignUpAlertScreen signUpAsValidCred(SignUpCred signUpCred) {
    log.atInfo().log("Sign up with valid credentials");

    return this.signUpWithCred(signUpCred)
      .submitSignUpSuccess();
  }

  public SignUpScreen signUpAsInvalidCred(SignUpCred signUpCred) {
    log.atInfo().log("Sign up with invalid credentials");
    return this.signUpWithCred(signUpCred)
      .submitSignUpFail();
  }

  public String getInvalidRepeatPasswordMessage() {
    log.atInfo().log("Get invalid repeat password message");

    return invalidRepeatPasswordElement().getText();
  }
}

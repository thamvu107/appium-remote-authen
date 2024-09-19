package screens.login;

import entity.authen.LoginCred;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SignInScreen extends LoginScreen {


  private final By signInButtonLocator = AppiumBy.accessibilityId("button-LOGIN");

  public SignInScreen(AppiumDriver driver) {
    super(driver);
    verifyScreenLoaded(signInButtonLocator);
  }

  public SignInScreen clickSignInBtn() {

    WebElement signInBtnEl = elementUtils.waitForElementTobeClickable(signInButtonLocator);
    signInBtnEl.click();

    return this;
  }

  public SignInScreen signInWithCred(LoginCred loginCred) {

    this.inputEmail(loginCred.getEmail())
      .inputPassword(loginCred.getPassword());
    this.clickSignInBtn();

    return this;
  }

//  public SignInAlertScreen signInAsValidCred(LoginCred loginCred) {
//    this.signInWithCred(loginCred);
//    return new SignInAlertScreen(driver);
//  }
//
//  public SignInScreen signInAsInvalidCred(LoginCred loginCred) {
//    this.signInWithCred(loginCred);
//
//    return this;
//  }

}

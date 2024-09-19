package testFlows;

import base.BaseFlow;
import entity.authen.LoginCred;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.testng.Assert;
import screens.alert.SignInAlertScreen;
import screens.login.LoginScreen;
import screens.login.SignInScreen;

public class SignInFlow extends BaseFlow {
  private final LoginScreen loginScreen;
  private SignInScreen signInScreen;
  private SignInAlertScreen alertScreen;

//  private final AlertHelper alertHelper;

  public SignInFlow(AppiumDriver driver) {
    super(driver);
    loginScreen = openLoginScreen();
    signInScreen = loginScreen.openSignInForm();
  }

  @Step("Close alert")
  public void closeAlert() {
    loginScreen.closeAlert();
  }

  @Step("Sign in with hidden credentials")
  public SignInFlow signInAsValidCred(LoginCred loginCred) {
    signInScreen = loginScreen.openSignInForm();

    signInScreen.signInWithCred(loginCred);
    alertScreen = new SignInAlertScreen(driver);

    return this;
  }

  @Step("Sign in with invalid cred")
  public SignInFlow signInAsInvalidCred(LoginCred loginCred) {
    signInScreen = loginScreen.openSignInForm();

    signInScreen.signInWithCred(loginCred);

    return this;
  }

  @Step("Verify alert is present")
  public SignInFlow verifyAlertIsPresent(String alertTitle, String alertMessage) {
    Assert.assertTrue(alertScreen.isAlertPresent(), "Alert is not present");
    Assert.assertEquals(alertScreen.getAlertTitle(), alertTitle, "Alert title is not correct");
    Assert.assertEquals(alertScreen.getAlertMessage(), alertMessage, "Alert title is not correct");

    return this;
  }

  @Step("Verify invalid email message")
  public SignInFlow verifyEmailIsInvalid(String email) {
    Assert.assertEquals(signInScreen.getInvalidEmailMessage(), email, "Invalid email message is not correct");

    return this;
  }

  @Step("Verify invalid password message")
  public SignInFlow verifyPasswordIsInvalid(String password) {
    Assert.assertEquals(signInScreen.getInvalidPasswordMessage(), password, "Invalid password message is not correct");

    return this;
  }

  @Step("Verify invalid email and password message")
  public SignInFlow verifyCredIsInvalid(String email, String password) {
    verifyEmailIsInvalid(email);
    verifyPasswordIsInvalid(password);

    return this;
  }

}

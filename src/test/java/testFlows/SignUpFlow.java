package testFlows;

import base.BaseFlow;
import entity.authen.SignUpCred;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import screens.alert.SignUpAlertScreen;
import screens.login.LoginScreen;
import screens.login.SignUpScreen;

public class SignUpFlow extends BaseFlow {

  private final LoginScreen loginScreen;
  private final SignUpScreen signUpScreen;
  private SignUpAlertScreen alertScreen;

  public SignUpFlow(AppiumDriver driver) {
    super(driver);


    loginScreen = openLoginScreen();
    signUpScreen = loginScreen.openSignUpForm();
  }

  public void closeAlert() {
    loginScreen.closeAlert();
  }

  public SignUpFlow signUpAsValidCred(SignUpCred signupCred) {

    signUpScreen.signUpWithCred(signupCred);
    alertScreen = new SignUpAlertScreen(driver);

    return this;
  }

  public SignUpFlow verifyAlertIsPresent(String alertTitle, String alertMessage) {
    Assert.assertTrue(alertScreen.isAlertPresent(), "Alert is not present");
    Assert.assertEquals(alertScreen.getAlertTitle(), alertTitle, "Alert title is not correct");
    Assert.assertEquals(alertScreen.getAlertMessage(), alertMessage, "Alert title is not correct");

    return this;
  }

  public SignUpFlow signUpAsInvalidCred(SignUpCred signupCred) {

    signUpScreen.signUpWithCred(signupCred);

    return this;
  }

  public void verifyEmailIsInvalid(String email) {
    Assert.assertEquals(signUpScreen.getInvalidEmailMessage(), email, "The invalid email message is not correct");
  }

  public void verifyPasswordIsInvalid(String password) {
    Assert.assertEquals(signUpScreen.getInvalidPasswordMessage(), password, "The invalid password message is not correct");
  }

  public void verifyRepeatPasswordIsInvalid(String repeatPassword) {
    Assert.assertEquals(signUpScreen.getInvalidRepeatPasswordMessage(), repeatPassword,
                        "The invalid repeat password message is not correct");
  }

  public void verifyCredIsInvalid(String email, String password, String repeatPassword) {
    verifyEmailIsInvalid(email);
    verifyPasswordIsInvalid(password);
    verifyRepeatPasswordIsInvalid(repeatPassword);
  }

}

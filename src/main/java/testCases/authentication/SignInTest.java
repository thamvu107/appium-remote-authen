package testCases.authentication;

import annotations.author.Author;
import base.BaseTest;
import dataProvider.signIn.LoginCredData;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import entity.authen.LoginCred;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testFlows.SignInFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static devices.MobileFactory.getEmulator;
import static interfaces.IAuthor.THAM_VU;

public class SignInTest extends BaseTest {

  private SignInFlow signInFlow;

  @BeforeClass(alwaysRun = true)
  public void beforeClass() {
    driverProvider = new DriverProvider();
    Capabilities caps = CapabilityFactory.getCaps(getEmulator());
//        Capabilities caps = CapabilityFactory.getCaps(getSimulator());
    driver = driverProvider.getLocalServerDriver(caps);
    signInFlow = new SignInFlow(driver);
  }

  @AfterMethod()
  public void afterMethod() {
    signInFlow.closeAlert();
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  public void loginWithCorrectCredential(LoginCred loginCred) {

    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  public void loginWithIncorrectCredentials(LoginCred loginCred) {

    signInFlow
      .signInAsInvalidCred(loginCred)
      .verifyCredIsInvalid(INVALID_EMAIL_MESSAGE, INVALID_PASSWORD_MESSAGE);
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidEmail", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  public void loginWithIncorrectEmail(LoginCred loginCred) {

    signInFlow.signInAsInvalidCred(loginCred)
      .verifyEmailIsInvalid(INVALID_EMAIL_MESSAGE);
  }


  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidPassword", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  public void loginWithIncorrectPassword(LoginCred loginCred) {

    signInFlow.signInAsInvalidCred(loginCred)
      .verifyPasswordIsInvalid(INVALID_PASSWORD_MESSAGE);
  }

  @Test(description = "Test case for purpose to show failure test",
    dataProvider = "loginCredInvalidEmail",
    dataProviderClass = LoginCredData.class,
    groups = {"brokenTests"})
  public void methodToBeBrokenTest1(LoginCred loginCred) {
    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }

  @Test(description = "Test case for purpose to show failure test",
    dataProvider = "loginCredInvalidEmail",
    dataProviderClass = LoginCredData.class,
    groups = {"NotInBrokenGroup"})
  public void methodToBeBrokenTest2(LoginCred loginCred) {
    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }
}

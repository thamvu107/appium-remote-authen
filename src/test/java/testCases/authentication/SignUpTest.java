package testCases.authentication;

import annotations.author.Author;
import base.BaseTest;
import dataProvider.signUp.SignUpCredData;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import entity.authen.SignUpCred;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import testFlows.SignUpFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.INCORRECT_REPEAT_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.SIGN_UP_SUCCESS_MESSAGE;
import static constants.SignUpScreenConstants.SIGN_UP_SUCCESS_TITLE;
import static devices.MobileFactory.getEmulator;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class SignUpTest extends BaseTest {

  private SignUpFlow signUpFlow;


  @BeforeClass
  public void beforeClass() {
    driverProvider = new DriverProvider();
//    Capabilities caps = CapabilityFactory.getCaps(getSimulator());
    Capabilities caps = CapabilityFactory.getCaps(getEmulator());
    driver = driverProvider.getLocalServerDriver(caps);
    setLogParams(caps);
    signUpFlow = new SignUpFlow(driver);
  }

  @AfterMethod
  public void afterMethod() {
    signUpFlow.closeAlert();
  }

  @Author(THAM_VU)
  @Test(dataProvider = "signUpCredValidUser", dataProviderClass = SignUpCredData.class,
    groups = {"Release"})
  public void signUpWithCorrectCredentials(SignUpCred signupCred) {
    signUpFlow.signUpAsValidCred(signupCred)
      .verifyAlertIsPresent(SIGN_UP_SUCCESS_TITLE, SIGN_UP_SUCCESS_MESSAGE);
  }

  @Author(THAM_VU)
  @Test(dataProvider = "signUpCredInvalidUser", dataProviderClass = SignUpCredData.class,
    groups = {"Release"})
  public void signUpWithInvalidUser(SignUpCred signUpCred) {

    signUpFlow.signUpAsInvalidCred(signUpCred)
      .verifyCredIsInvalid(INVALID_EMAIL_MESSAGE, INVALID_PASSWORD_MESSAGE, INCORRECT_REPEAT_PASSWORD_MESSAGE);

  }

  @Author(THAM_VU)
  @Test(dataProvider = "signUpCredInvalidRepeatPassword", dataProviderClass = SignUpCredData.class,
    groups = {"Release"})
  public void signUpWithInvalidRepeatPassWord(SignUpCred signUpCred) {

    signUpFlow.signUpAsInvalidCred(signUpCred)
      .verifyRepeatPasswordIsInvalid(INCORRECT_REPEAT_PASSWORD_MESSAGE);
  }

  @Test
  @Ignore
  public void ignoreTest() {
    System.out.println("Ignore test");
  }
}

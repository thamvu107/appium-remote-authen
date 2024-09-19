package testcaseV9;

import annotations.author.Author;
import base.BaseTestV9;
import dataProvider.signIn.LoginCredData;
import entity.authen.LoginCred;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import testFlows.SignInFlow;

import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class ReportableTestStatus extends BaseTestV9 {
  @Author(THAM_VU)
  @Test(enabled = false, dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  public void skipTest(LoginCred loginCred) {
    SignInFlow signInFlow = signInFlow();
    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }

  @Test(description = "Test case for purpose to show failure test",
    dataProvider = "loginCredInvalidEmail",
    dataProviderClass = LoginCredData.class,
    groups = {"brokenTests"})
  public void brokenTest(LoginCred loginCred) {
    SignInFlow signInFlow = signInFlow();
    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }

  @Test(description = "Test case for purpose to show failure test",
    dataProvider = "loginCredInvalidEmail",
    dataProviderClass = LoginCredData.class,
    groups = {"brokenTests"})
  public void brokenTest2(LoginCred loginCred) {
    SignInFlow signInFlow = signInFlow();
    Assert.fail("Test case for purpose to show failure test");
    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }
}

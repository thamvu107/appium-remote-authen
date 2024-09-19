package testcaseV10.authen;

import annotations.author.Author;
import base.BaseTestV10;
import dataProvider.signIn.LoginCredData;
import entity.authen.LoginCred;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.qameta.allure.testng.Tag;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import testFlows.SignInFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static interfaces.IAuthor.THAM_VU;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Slf4j
@Epic("Authentication")
@Feature("Sign In")
public class SignInTest extends BaseTestV10 {

  @Test(dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  @Severity(CRITICAL)
  @Description("Login with correct credential")
  @Story("As a user, I want to login with correct credential")
  @Author(THAM_VU)
  @Tag("Authentication")
  public void loginWithCorrectCredential(LoginCred loginCred) {

    SignInFlow signInFlow = signInFlow();
    signInFlow.signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);

    Allure.getLifecycle().updateTestCase(testCase -> testCase.setName("Login with correct credential"));
    hideParametersForTestCasesInAllureReport();
  }

  @Test(dataProvider = "loginCredInvalidEmail", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  @Owner(THAM_VU)
  @Severity(NORMAL)
  @Issue("AUTH-123")
  @TmsLink("TMS-123")
  @Description("Login with incorrect email")
  @Tag("Authentication")
  public void loginWithIncorrectEmail(LoginCred loginCred) {

    SignInFlow signInFlow = signInFlow();
    signInFlow.signInAsInvalidCred(loginCred)
      .verifyEmailIsInvalid(INVALID_EMAIL_MESSAGE);

    Allure.getLifecycle().updateTestCase(testCase -> testCase.setName("Login with incorrect email"));
    hideParametersForTestCasesInAllureReport();
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  @Tag("Authentication")
  public void loginWithIncorrectCredentials(LoginCred loginCred) {

    SignInFlow signInFlow = signInFlow();
    signInFlow.signInAsInvalidCred(loginCred)
      .verifyCredIsInvalid(INVALID_EMAIL_MESSAGE, INVALID_PASSWORD_MESSAGE);

    Allure.getLifecycle().updateTestCase(testCase -> testCase.setName("Login with incorrect credentials"));
    hideParametersForTestCasesInAllureReport();
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidPassword", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  @Tag("Authentication")
  public void loginWithIncorrectPassword(LoginCred loginCred) {

    SignInFlow signInFlow = signInFlow();
    signInFlow.signInAsInvalidCred(loginCred)
      .verifyPasswordIsInvalid(INVALID_PASSWORD_MESSAGE);

    Allure.getLifecycle().updateTestCase(testCase -> testCase.setName("Login with incorrect password"));
    hideParametersForTestCasesInAllureReport();
  }
}

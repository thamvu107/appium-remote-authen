package testcaseV10.authen;

import annotations.author.Author;
import base.BaseTestV10;
import dataProvider.signUp.SignUpCredData;
import driver.ThreadSafeDriver;
import entity.authen.SignUpCred;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.Tag;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import testFlows.SignUpFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.INCORRECT_REPEAT_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.SIGN_UP_SUCCESS_MESSAGE;
import static constants.SignUpScreenConstants.SIGN_UP_SUCCESS_TITLE;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class SignUpTest extends BaseTestV10 {

  @Author(THAM_VU)
  @Test(dataProvider = "signUpCredValidUser", dataProviderClass = SignUpCredData.class,
    groups = {"Release"})
  @Tag("SignUp")
  public void signUpWithCorrectCredentials(SignUpCred signupCred) {

    SignUpFlow signUpFlow = signUpFlow();
    signUpFlow.signUpAsValidCred(signupCred)
      .verifyAlertIsPresent(SIGN_UP_SUCCESS_TITLE, SIGN_UP_SUCCESS_MESSAGE);

    Allure.getLifecycle().updateTestCase(testCase -> testCase.setName("Signup with correct credentials"));
    hideParametersForTestCasesInAllureReport();
  }

  @Author(THAM_VU)
  @Test(dataProvider = "signUpCredInvalidUser", dataProviderClass = SignUpCredData.class,
    groups = {"Release"})
  @Tag("SignUp")
  public void signUpWithInvalidCredentials(SignUpCred signUpCred) {

    SignUpFlow signUpFlow = signUpFlow();
    signUpFlow.signUpAsInvalidCred(signUpCred)
      .verifyCredIsInvalid(INVALID_EMAIL_MESSAGE, INVALID_PASSWORD_MESSAGE, INCORRECT_REPEAT_PASSWORD_MESSAGE);

    Allure.getLifecycle().updateTestCase(testCase -> testCase.setName("Signup with invalid credentials"));
    hideParametersForTestCasesInAllureReport();
  }

  @Author(THAM_VU)
  @Test(dataProvider = "signUpCredInvalidRepeatPassword", dataProviderClass = SignUpCredData.class,
    groups = {"Release"})
  @Tag("SignUp")
  public void signUpWithInvalidRepeatPassWord(SignUpCred signUpCred) {

    SignUpFlow signUpFlow = signUpFlow();
    signUpFlow.signUpAsInvalidCred(signUpCred)
      .verifyRepeatPasswordIsInvalid(INCORRECT_REPEAT_PASSWORD_MESSAGE);

    Allure.getLifecycle().updateTestCase(testCase -> testCase.setName("Signup with incorrect repeat password"));
    hideParametersForTestCasesInAllureReport();
  }

  private SignUpFlow signUpFlow() {
    return new SignUpFlow(ThreadSafeDriver.getDriver());
  }

}

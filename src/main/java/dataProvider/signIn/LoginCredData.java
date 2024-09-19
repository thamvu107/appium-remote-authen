package dataProvider.signIn;

import constants.filePaths.jsonFiles.LoginCredPathConstants;
import entity.authen.LoginCred;
import org.testng.annotations.DataProvider;
import utils.DataObjectBuilderUtil;

import java.nio.file.Path;

public class LoginCredData {
  @DataProvider()
  public LoginCred[] loginCredValidUser() {

    Path loginCredDataPath = Path.of(LoginCredPathConstants.LOGIN_CRED_VALID_USER_JSON);

    return DataObjectBuilderUtil.buildDataObject(loginCredDataPath, LoginCred[].class);
  }

  @DataProvider()
  public LoginCred[] loginCredInvalidUser() {

    Path loginCredDataPath = Path.of(LoginCredPathConstants.LOGIN_CRED_INVALID_USER_JSON);

    return DataObjectBuilderUtil.buildDataObject(loginCredDataPath, LoginCred[].class);
  }

  @DataProvider()
  public LoginCred[] loginCredInvalidEmail() {

    Path loginCredDataPath = Path.of(LoginCredPathConstants.LOGIN_CRED_INVALID_EMAIL_JSON);

    return DataObjectBuilderUtil.buildDataObject(loginCredDataPath, LoginCred[].class);
  }

  @DataProvider()
  public LoginCred[] loginCredInvalidPassword() {

    Path loginCredDataPath = Path.of(LoginCredPathConstants.LOGIN_CRED_INVALID_PASSWORD_JSON);

    return DataObjectBuilderUtil.buildDataObject(loginCredDataPath, LoginCred[].class);
  }
}

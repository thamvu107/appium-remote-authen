package constants.filePaths.jsonFiles;

import java.io.File;

public interface LoginCredPathConstants extends JsonFileConstants {
//    public static final String SIGN_IN_JSON_PATH = JSON_BASE_PATH + "signInCred" + File.separator;
//    public static final String LOGIN_CRED_VALID_USER_JSON = SIGN_IN_JSON_PATH + "LoginCredValidUser" + JSON_SUFFIX;
//    public static final String LOGIN_CRED_INVALID_USER_JSON = SIGN_IN_JSON_PATH + "LoginCredInvalidUser" + JSON_SUFFIX;
//    public static final String LOGIN_CRED_INVALID_EMAIL_JSON = SIGN_IN_JSON_PATH + "LoginCredInvalidEmail" + JSON_SUFFIX;
//    public static final String LOGIN_CRED_INVALID_PASSWORD_JSON = SIGN_IN_JSON_PATH + "LoginCredInvalidPassword" + JSON_SUFFIX;

    String SIGN_IN_JSON_PATH = JSON_BASE_PATH + "signInCred" + File.separator;
    String LOGIN_CRED_VALID_USER_JSON = SIGN_IN_JSON_PATH + "LoginCredValidUser" + JSON_SUFFIX;
    String LOGIN_CRED_INVALID_USER_JSON = SIGN_IN_JSON_PATH + "LoginCredInvalidUser" + JSON_SUFFIX;
    String LOGIN_CRED_INVALID_EMAIL_JSON = SIGN_IN_JSON_PATH + "LoginCredInvalidEmail" + JSON_SUFFIX;
    String LOGIN_CRED_INVALID_PASSWORD_JSON = SIGN_IN_JSON_PATH + "LoginCredInvalidPassword" + JSON_SUFFIX;

}

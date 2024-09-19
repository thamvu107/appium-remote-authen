package constants.filePaths.jsonFiles;

import java.io.File;

public interface SignUpCredPathConstants extends JsonFileConstants {
    String SIGNUP_CRED_PATH = JSON_BASE_PATH + "signUpCred" + File.separator;
    String SIGNUP_CRED_VALID_USER_JSON = SIGNUP_CRED_PATH + "SignUpCredValidUser" + JSON_SUFFIX;
    String SIGNUP_CRED_INVALID_USER_JSON = SIGNUP_CRED_PATH + "SignUpCredInvalidUser" + JSON_SUFFIX;
    String SIGNUP_CRED_INVALID_REPEAT_PASSWORD_JSON = SIGNUP_CRED_PATH + "SignUpCredInvalidRepeatPassword" + JSON_SUFFIX;
}

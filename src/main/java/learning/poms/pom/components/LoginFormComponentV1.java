package learning.poms.pom.components;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginFormComponentV1 {
  private final AppiumDriver driver;
  private final static By emailLoc = AppiumBy.accessibilityId("input-email");
  private final static By passwordLoc = AppiumBy.accessibilityId("input-password");
  private final By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");


  public LoginFormComponentV1(AppiumDriver driver) {
    this.driver = driver;
  }

  public LoginFormComponentV1 inputEmail(String usernameText) {
    driver.findElement(emailLoc).sendKeys(usernameText);

    return this;
  }

  public LoginFormComponentV1 inputPassword(String passwordText) {
    driver.findElement(passwordLoc).sendKeys(passwordText);

    return this;
  }

  public LoginFormComponentV1 clickLoginBtn() {
    driver.findElement(loginBtnLoc).click();
    return this;
  }
}

package learning.poms.pom.pages;

import io.appium.java_client.AppiumDriver;
import learning.poms.pom.components.LoginFormComponentV1;

public class BasePage1 {

  protected AppiumDriver driver;

  private final LoginFormComponentV1 loginFormComponent;


  public BasePage1(AppiumDriver driver) {
    this.driver = driver;
    this.loginFormComponent = new LoginFormComponentV1(driver);
  }

  public LoginFormComponentV1 loginFormComponent() {
    return this.loginFormComponent;
  }
}

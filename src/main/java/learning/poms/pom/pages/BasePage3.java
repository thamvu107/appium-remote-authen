package learning.poms.pom.pages;

import io.appium.java_client.AppiumDriver;
import learning.poms.pom.components.LoginFormComponentV2;

public class BasePage3 {

  protected AppiumDriver driver;

  private final LoginFormComponentV2 loginFormComponentV2;


  public BasePage3(AppiumDriver driver) {
    this.driver = driver;
    this.loginFormComponentV2 = new LoginFormComponentV2(driver);
  }

  //  public LoginFormComponent loginFormComponent() {
//    return this.loginFormComponent;
//  }
  public LoginFormComponentV2 loginFormComponent() {
    return this.loginFormComponentV2;
  }
}

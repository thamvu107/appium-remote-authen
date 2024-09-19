package learning.poms.pom.pages;

import io.appium.java_client.AppiumDriver;
import utils.InteractionUtils;

public class LoginPage1 extends BasePage1 {
  private final AppiumDriver driver;
  protected InteractionUtils interactionUtils;


  public LoginPage1(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
    this.interactionUtils = new InteractionUtils(driver);

  }

  public boolean isLoginSuccess() {
    return interactionUtils.isAlertPresent();
  }

}

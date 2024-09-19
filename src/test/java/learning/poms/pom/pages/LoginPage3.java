package learning.poms.pom.pages;

import io.appium.java_client.AppiumDriver;
import utils.InteractionUtils;

public class LoginPage3 extends BasePage3 {
  private final AppiumDriver driver;
  protected InteractionUtils interactionUtils;


  public LoginPage3(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
    this.interactionUtils = new InteractionUtils(driver);

  }

  public boolean isLoginSuccess() {
    return interactionUtils.isAlertPresent();
  }

}

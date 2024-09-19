package learning.poms.pom.pages;

import io.appium.java_client.AppiumDriver;
import utils.InteractionUtils;

public class LoginPage2 extends BasePage2 {
  private final AppiumDriver driver;
  protected InteractionUtils interactionUtils;


  public LoginPage2(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
    this.interactionUtils = new InteractionUtils(driver);

  }

  public boolean isLoginSuccess() {
    return interactionUtils.isAlertPresent();
  }

}

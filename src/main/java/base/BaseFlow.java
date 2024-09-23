package base;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import screens.login.LoginScreen;

public class BaseFlow {

  protected final AppiumDriver driver;

  public BaseFlow(AppiumDriver driver) {
    this.driver = driver;
  }

  @Step("Open login screen")
  public LoginScreen openLoginScreen() {

    return new BaseScreen(this.driver).openLoginScreen();
  }

}

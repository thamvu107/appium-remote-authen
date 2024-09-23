package learning.poms.pom.components;

import annotations.selectors.ComponentByXpath;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@ComponentByXpath("//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]")
public class LoginFormComponentV2 extends Component {
  private final AppiumDriver driver;
  private final static By emailLoc = AppiumBy.accessibilityId("input-email");
  private final static By passwordLoc = AppiumBy.accessibilityId("input-password");
  private final By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");
  private final WebElement myComponentEl;


  public LoginFormComponentV2(AppiumDriver driver, WebElement componentEl) {
    super(driver, componentEl);
    this.driver = driver;
    this.myComponentEl = componentEl;
  }

  public LoginFormComponentV2(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
    this.myComponentEl = findComponent(this.getClass());
  }

  public LoginFormComponentV2 inputEmail(String usernameText) {
//    driver.findElement(emailLoc).sendKeys(usernameText);
    this.myComponentEl.findElement(emailLoc).sendKeys(usernameText);

    return this;
  }

  public LoginFormComponentV2 inputPassword(String passwordText) {
//    driver.findElement(passwordLoc).sendKeys(passwordText);
    this.myComponentEl.findElement(passwordLoc).sendKeys(passwordText);

    return this;
  }

  public LoginFormComponentV2 clickLoginBtn() {
//    driver.findElement(loginBtnLoc).click();
    this.myComponentEl.findElement(loginBtnLoc).click();

    return this;
  }


}

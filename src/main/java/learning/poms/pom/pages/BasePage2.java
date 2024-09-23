package learning.poms.pom.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import learning.poms.pom.components.LoginFormComponentV2;
import org.openqa.selenium.WebElement;

public class BasePage2 {

  protected AppiumDriver driver;

  private final LoginFormComponentV2 loginFormComponentV2;


  public BasePage2(AppiumDriver driver) {
    this.driver = driver;

    // Example of finding the parent element using XPath
    WebElement loginButton = driver.findElement(AppiumBy.accessibilityId("Login-screen"));
    WebElement componentEl = loginButton.findElement(AppiumBy.xpath(
      "//android.widget.ScrollView[@content-desc=\"Login-screen\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]"));

    this.loginFormComponentV2 = new LoginFormComponentV2(driver, componentEl);
//    this.loginFormComponentV2 = new LoginFormComponentV2(driver);
  }

  //  public LoginFormComponent loginFormComponent() {
//    return this.loginFormComponent;
//  }
  public LoginFormComponentV2 loginFormComponent() {
    return this.loginFormComponentV2;
  }
}

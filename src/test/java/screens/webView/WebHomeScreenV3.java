package screens.webView;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WebHomeScreenV3 extends WebBaseScreen {
  private final AppiumDriver driver;
  private final By homeScreenLoc = By.cssSelector(".hero__title");
//  private final By homeScreenLoc = By.id(".__docusaurus");

  public WebHomeScreenV3(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
    verifyScreenTitle("WebdriverIO · Next-gen browser and mobile automation test framework for Node.js | WebdriverIO");
  }


}

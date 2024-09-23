package screens.alert;

import base.BaseScreen;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.id;

public abstract class AlertScreen extends BaseScreen {

  // TODO: Separate locators by platform file.

  // Android
  protected final By androidAlertLoc = id("android:id/content");
  protected final By androidAlertTitleLoc = id("android:id/alertTitle");
  protected final By androidAlertMessageLoc = id("android:id/message");
  protected final By androidAlertButtonLoc = id("android:id/button1");

  //iOS
  protected final By iosAlertLocator = className("XCUIElementTypeAlert");
  protected final By iosAlertButtonLoc = accessibilityId("OK");

  protected AlertScreen(AppiumDriver driver) {

    super(driver);
    verifyScreenLoaded(alertTitleLocator());
  }

  protected final Map<PlatformType, By> alertLocatorMap = Map.of(
    PlatformType.ANDROID, androidAlertLoc,
    PlatformType.IOS, iosAlertLocator);

  protected final Map<PlatformType, By> alertOkButtonLocatorMap = Map.of(
    PlatformType.ANDROID, androidAlertButtonLoc,
    PlatformType.IOS, iosAlertButtonLoc);

  protected By alertTitleLocator() {
//        return alertLocatorMap.get(currentPlatform);
    return elementUtils.getLocator(alertLocatorMap);
  }

  protected WebElement alertEl() {

    return elementUtils.findElement(alertLocatorMap);
  }

  protected abstract WebElement alertTitleEl();

  protected abstract WebElement alertMessageEl();

  protected WebElement okButtonEl() {

    return elementUtils.findElement(alertOkButtonLocatorMap);
  }

  public boolean isAlertPresent() {

    return interactionUtils.isAlertPresent();
  }

  public String getAlertTitle() {

    return alertTitleEl().getText();
  }

  public String getAlertMessage() {

    return alertMessageEl().getText();
  }

  public AlertScreen accept() {

    okButtonEl().click();

    return this;
  }

  public boolean isAlertDisappeared() {

    return interactionUtils.assertAlertHasDisappeared(driver, Duration.ofMillis(500));
  }

  public void acceptAlertIfPresent() {
    if (isAlertPresent()) {
      try {
        Alert alert = driver.switchTo().alert();
        alert.accept();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  public void acceptAlert() {
    try {
      Alert alert = driver.switchTo().alert();
      alert.accept();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

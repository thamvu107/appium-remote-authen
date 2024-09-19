package screens.alert;

import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;

public class FormComponentAlertScreen extends AlertScreen {
  private final By iosAlertTitleLoc =
    iOSNsPredicateString("name == \"This button is\" AND label == \"This button is\" AND value == \"This button is\"");
  private final By iosAlertMessageLoc = accessibilityId("This button is active");

  protected FormComponentAlertScreen(AppiumDriver driver) {
    super(driver);
    verifyScreenLoaded(elementUtils.getLocator(dialogTitleLocMap));
  }

  private final Map<PlatformType, By> dialogTitleLocMap = Map.of(
    PlatformType.ANDROID, androidAlertTitleLoc,
    PlatformType.IOS, iosAlertTitleLoc);

  private final Map<PlatformType, By> dialogMessageLocMap = Map.of(
    PlatformType.ANDROID, androidAlertMessageLoc,
    PlatformType.IOS, iosAlertMessageLoc);


  @Override
  protected WebElement alertTitleEl() {
    return null;
  }

  @Override
  protected WebElement alertMessageEl() {
    return null;
  }

}

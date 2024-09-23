package screens.setting;

import base.BaseScreen;
import entity.app.AndroidAppUnderTest;
import entity.app.IOSAppUnderTest;
import enums.PlatformType;
import interfaces.app.ISettingApp;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Map;

public class SettingScreen extends BaseScreen {
  private final AppiumDriver driver;

  public SettingScreen(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
  }

  private final Map<PlatformType, String> appUnderTestIdMap = Map.of(
    PlatformType.ANDROID, new AndroidAppUnderTest().getAppPackage(),
    PlatformType.IOS, new IOSAppUnderTest().getBundleId()
  );
  private final Map<PlatformType, String> setttingAppIdMap = Map.of(
    PlatformType.ANDROID, ISettingApp.APP_PACKAGE,
    PlatformType.IOS, ISettingApp.BUNDLE_ID
  );
  private final Map<PlatformType, By> settingPageLoc = Map.of(
    PlatformType.ANDROID, IAndroidSettingLocator.searchSettingBox,
    PlatformType.IOS, IIOSSettingLocator.settingPageTitle
  );

  private String getAppUnderTestId() {
    return appUnderTestIdMap.get(currentPlatform);
  }

  private String getSettingAppId() {
    return setttingAppIdMap.get(currentPlatform);
  }


  private By settingPageLoc() {

    return elementUtils.getLocator(settingPageLoc);

  }

  public SettingScreen switchSettingApp() {


    switchToActiveApp(currentPlatform, getSettingAppId());

    return this;
  }

  public SettingScreen switchAppUnderTest() {

    switchToActiveApp(currentPlatform, getAppUnderTestId());

    return this;
  }


  public WebElement seeSettingPage() {
    WebElement settingPage = elementUtils.waitForElementToBeVisible(settingPageLoc());
    return settingPage;
  }

  public SettingScreen runAppInBackground() {
    // Duration.ofSeconds(-1) - put till we call it back
    switch (currentPlatform) {
      case ANDROID:
        ((AndroidDriver) driver).runAppInBackground(Duration.ofSeconds(-1));
        break;
      case IOS:
        ((IOSDriver) driver).runAppInBackground(Duration.ofSeconds(-1));
        break;
    }

    return this;
  }

  private void switchToActiveApp(PlatformType platformType, String appId) {
//        androidDriver.activateApp("com.android.settings");
    switch (platformType) {
      case ANDROID:
        ((AndroidDriver) driver).activateApp(appId);
        break;
      case IOS:
        ((IOSDriver) driver).activateApp(appId);

//                iosDriver.activateApp("com.wdiodemoapp");

//                iosDriver.executeScript("mobile: launchApp", ImmutableMap.of("bundleId", "com.example.firstapp"));
        break;

    }
  }

}

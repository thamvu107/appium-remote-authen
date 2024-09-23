package screens.setting;

import org.openqa.selenium.By;

import static io.appium.java_client.AppiumBy.iOSNsPredicateString;

public interface IIOSSettingLocator {
  By settingPageTitle = iOSNsPredicateString("name == \"Settings\" AND label == \"Settings\" AND value == \"Settings\"");
  String appId = "com.apple.Preferences";
}

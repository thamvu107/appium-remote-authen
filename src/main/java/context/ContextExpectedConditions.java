package context;

import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utils.PlatformUtil;

public class ContextExpectedConditions {
  private ContextExpectedConditions() {
  }

  public static ExpectedCondition<Boolean> hasMultipleContexts(AppiumDriver driver) {
    return new ExpectedCondition<Boolean>() {
      final PlatformType currentPlatform = new PlatformUtil(driver).getCurrentPlatform();

      @Override
      public Boolean apply(WebDriver input) {
        return switch (currentPlatform) {
          case ANDROID -> ((AndroidDriver) driver).getContextHandles().size() > 1;
          case IOS -> ((IOSDriver) driver).getContextHandles().size() > 1;
          default -> throw new PlatformNotSupportException("Platform not supported: " + currentPlatform);
        };
      }

      public String toString() {
        return String.format("%s platform is more than one context", currentPlatform);
      }
    };

  }


  public static ExpectedCondition<Boolean> hasSingleContext(AppiumDriver driver) {
    return new ExpectedCondition<Boolean>() {
      final PlatformType currentPlatform = new PlatformUtil(driver).getCurrentPlatform();

      @Override
      public Boolean apply(WebDriver input) {
        return switch (currentPlatform) {
          case ANDROID -> ((AndroidDriver) driver).getContextHandles().size() == 1;
          case IOS -> ((IOSDriver) driver).getContextHandles().size() == 1;
          default -> throw new PlatformNotSupportException("Platform not supported: " + currentPlatform);
        };
      }

      public String toString() {
        return String.format("%s platform is only one context", currentPlatform);
      }
    };

  }


}

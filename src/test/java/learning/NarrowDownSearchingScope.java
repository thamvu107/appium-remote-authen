package learning;

import constants.WaitConstants;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import enums.PlatformType;
import exceptions.swipe.vertical.SwipeUpException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ElementUtils;
import utils.InteractionUtils;
import utils.NotificationUtils;
import utils.WaitUtils;
import utils.gestures.swipe.vertical.SwipeHalfBottomScreen;

import java.util.List;
import java.util.Map;

import static devices.MobileFactory.getEmulator;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;
import static io.appium.java_client.AppiumBy.id;

public class NarrowDownSearchingScope {

  protected AppiumDriver driver;
  protected static WebDriverWait wait;
  protected static FluentWait<AppiumDriver> fluentWait;
  protected InteractionUtils mobileInteractionHelper;
  protected WaitUtils waitUtils;
  private ElementUtils elementUtils;

  DriverProvider driverProvider;

  @BeforeClass
  public void setUpAppium() {

//        this.driver = DriverFactory.getLocalServerDriver(MobileFactory.getEmulator());
    driverProvider = new DriverProvider();
    Capabilities caps = CapabilityFactory.getCaps(getEmulator());
    driver = driverProvider.getLocalServerDriver(caps);

    WaitUtils waitHelper = new WaitUtils(driver);
    wait = waitHelper.explicitWait();
    fluentWait = waitHelper.fluentWait(WaitConstants.SHORT_FLUENT_WAIT, WaitConstants.POLLING_EVERY);
    mobileInteractionHelper = new InteractionUtils(this.driver);
    waitUtils = new WaitUtils(driver);
    elementUtils = new ElementUtils(driver);
  }

  @AfterClass
  public void tearDown() {
    driverProvider.quitDriver(driver);
  }

  @Test
  public void narrowDownSearchingScope() {
    final String NOTIFICATION_TITLE = "Appium Settings";
    final String NOTIFICATION_MESSAGE =
      "Keep this service running, so Appium for Android can properly interact with several system APIs";
    boolean notificationFound = false;

    NotificationUtils notificationHelper = new NotificationUtils(driver);

    try {
      By formsBtnLoc = AppiumBy.accessibilityId("Forms");
      elementUtils.waitForFindingElement(formsBtnLoc);
      driver.findElement(formsBtnLoc).click();

      Map<PlatformType, By> formComponentLocatorMap = Map.of(
        PlatformType.ANDROID, androidUIAutomator("new UiSelector(). textContains(\"Form components\")"),
        PlatformType.IOS,
        iOSNsPredicateString("name == \"Form components\" AND label == \"Form components\" AND value == \"Form components\"")
      );
      By formComponentLoc = elementUtils.getLocator(formComponentLocatorMap);
      elementUtils.waitForFindingElement(formComponentLoc);


      notificationHelper.openNotificationPanel();

      // TODO: Simulator notification
      Map<PlatformType, By> notificationLocatorMap = Map.of(
        PlatformType.ANDROID, id("com.android.systemui:id/expanded"),
        PlatformType.IOS, id("com.android.systemui:id/expanded")
      );
      By notificationLoc = elementUtils.getLocator(notificationLocatorMap);
      elementUtils.waitForFindingElement(notificationLoc);


      // TODO: handle app_name_text
//            By notificationTitleLoc = AppiumBy.id("android:id/app_name_text");

      Map<PlatformType, By> notificationTitleLocatorMap = Map.of(
        PlatformType.ANDROID, id("android:id/title"),
        PlatformType.IOS, id("android:id/title")
      );
      By notificationTitleLoc = elementUtils.getLocator(notificationTitleLocatorMap);
      elementUtils.waitForFindingElement(notificationTitleLoc);


      Map<PlatformType, By> notificationMessageLocatorMap = Map.of(
        PlatformType.ANDROID, id("android:id/big_text"),
        PlatformType.IOS, id("android:id/big_text")
      );
      By notificationMessageLoc = elementUtils.getLocator(notificationMessageLocatorMap);
      elementUtils.waitForFindingElement(notificationMessageLoc);


      List<WebElement> notificationEleList = driver.findElements(notificationLoc);

      Assert.assertFalse(notificationEleList.isEmpty(), "No notifications found");

      for (WebElement notificationEle : notificationEleList) {

        // Narrow down searching scope
        WebElement notificationTitleEle = notificationEle.findElement(notificationTitleLoc);
        WebElement notificationMessageEle = notificationEle.findElement(notificationMessageLoc);
        if (elementUtils.isTextDisplayedCorrect(notificationTitleEle, NOTIFICATION_TITLE) &&
          elementUtils.isTextDisplayedCorrect(notificationMessageEle, NOTIFICATION_MESSAGE)) {
          notificationFound = true;
          break;
        }
      }
      Assert.assertTrue(notificationFound);
      notificationHelper.closeNotificationPanel();

      SwipeHalfBottomScreen swipe = new SwipeHalfBottomScreen(driver);
      swipe.swipeUp();


    } catch (SwipeUpException ex) {
      ex.printStackTrace();

    } catch (Exception e) {
      Assert.fail("No notifications found " + e.getMessage());
      e.printStackTrace();
    }
  }

}

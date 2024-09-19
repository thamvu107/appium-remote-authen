package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InteractionUtils {
    private final AppiumDriver driver;
    private final WaitUtils waitUtils;

    public InteractionUtils(AppiumDriver driver) {
        this.driver = driver;
        waitUtils = new WaitUtils(driver);
    }


    public void sendKeys(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void setText(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public boolean isAlertPresent() {
        try {
            waitUtils.explicitWait().until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean assertAlertHasDisappeared(AppiumDriver driver, Duration timeoutInMilliseconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInMilliseconds);

        try {
            return wait.until(dr -> {
                try {
                    dr.switchTo().alert();
                    return false; // Alert is still present
                } catch (NoAlertPresentException e) {
                    return true; // Alert has disappeared
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

}

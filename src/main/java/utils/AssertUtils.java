package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertUtils {

    private final ElementUtils elementUtils;
    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public AssertUtils(AppiumDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public void verifyElementDisplayed(By locator) {
        Assert.assertTrue(elementUtils.isElementDisplayed(locator));
    }

    public void verifyElementDisplayed(WebElement webElement) {
        Assert.assertTrue(elementUtils.isElementDisplayed(webElement));
    }

}

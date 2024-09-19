package utils;

import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Require;

import java.util.List;
import java.util.Map;

public class LocatorMapperUtils {
    private final AppiumDriver driver;
    //    private final String currentPlatform;
    private final PlatformType currentPlatform;

    // TODO: try the way handle Element mapper.

    public LocatorMapperUtils(AppiumDriver driver) {
        this.driver = driver;
        this.currentPlatform = new PlatformUtil(this.driver).getCurrentPlatform();
    }

    public WebElement findElement(Map<PlatformType, By> locatorMap) {

//        By elementLocator = locatorMap.get(Platform.valueOf(currentPlatform));
        By elementLocator = locatorMap.get(currentPlatform);

        return this.driver.findElement(elementLocator);
    }

    public List<WebElement> findElements(Map<PlatformType, By> locatorMap) {

//        By elementLocator = locatorMap.get(Platform.valueOf(currentPlatform));
        By elementLocator = locatorMap.get(currentPlatform);

        return this.driver.findElements(elementLocator);
    }

    // MobilePlatform
    public By getLocatorMapped(Map<PlatformType, By> locatorMap) {

        Require.nonNull("Locator", locatorMap);

//        return locatorMap.get(Platform.valueOf(currentPlatform));
        return locatorMap.get(currentPlatform);
    }

    /* Check if an element is existed by its locator */
    public boolean isElementPresent(Map<PlatformType, By> locatorMap) {

        List<WebElement> elements = this.findElements(locatorMap);

        return !elements.isEmpty();
    }

}

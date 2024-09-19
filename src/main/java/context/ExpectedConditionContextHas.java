package context;

import enums.Contexts;
import enums.PlatformType;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utils.PlatformUtil;
import utils.ValidationDriver;

import java.util.Set;

public class ExpectedConditionContextHas implements ExpectedCondition<Boolean> {
    private final AppiumDriver driver;
    private final String contextName;
    private final PlatformType currentPlatform;

    public ExpectedConditionContextHas(AppiumDriver driver, Contexts context) {
        ValidationDriver.validateDriverNotNull(driver);
        this.driver = driver;
        this.contextName = context.getContextName();
        this.currentPlatform = new PlatformUtil(driver).getCurrentPlatform();
    }

    @Override
    public Boolean apply(WebDriver input) {
        Set<String> contexts;

        switch (currentPlatform) {
            case ANDROID:
                contexts = ((AndroidDriver) driver).getContextHandles();
                return contexts.size() > 1 && contexts.contains(contextName);
            case IOS:
                contexts = ((IOSDriver) driver).getContextHandles();
                return contexts.size() > 1 && contexts.contains(contextName);
            default:
                throw new PlatformNotSupportException("Platform not supported: " + currentPlatform);
        }
    }

    public String toString() {
        return String.format("%s platform is more than one context", currentPlatform);
    }
}

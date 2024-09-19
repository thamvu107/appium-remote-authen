package utils.gestures.swipe.vertical;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import static constants.SwipeConstants.MOVE_DURATION;


public class SwipeHalfTopScreen extends SwipeVertically {
    public SwipeHalfTopScreen(AppiumDriver driver) {
        super(driver, 0.5f, 0, 0.5f, MOVE_DURATION);
    }

    public SwipeHalfTopScreen(AppiumDriver driver, WebElement wrapper) {
        super(driver, wrapper, 0.5f, 0, 0.5f, MOVE_DURATION);
    }

}

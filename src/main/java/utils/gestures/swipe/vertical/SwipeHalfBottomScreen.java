package utils.gestures.swipe.vertical;

import io.appium.java_client.AppiumDriver;

import static constants.SwipeConstants.MOVE_DURATION;

public class SwipeHalfBottomScreen extends SwipeVertically {

    public SwipeHalfBottomScreen(AppiumDriver driver) {
        super(driver, 0.5f, 0.5f, 1, MOVE_DURATION);
    }

}

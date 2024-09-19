package utils;

import io.appium.java_client.AppiumDriver;
import utils.gestures.swipe.vertical.SwipeHalfTopScreen;

public class NotificationUtils {
    SwipeHalfTopScreen swipe;

    public NotificationUtils(AppiumDriver driver) {
        swipe = new SwipeHalfTopScreen(driver);
    }

    public void openNotificationPanel() {

        swipe.swipeDown();
    }

    public void closeNotificationPanel() {

        swipe.swipeUp();

    }
}

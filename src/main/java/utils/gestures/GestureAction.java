package utils.gestures;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

import static constants.WaitConstants.QUICK_PAUSE;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public interface GestureAction {

    default void performGesture(AppiumDriver driver, Point start, Point end, long moveDuration) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence performGesture = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, viewport(), start.x, start.y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, ofMillis(QUICK_PAUSE)))
                .addAction(finger.createPointerMove(ofMillis(moveDuration), viewport(), end.x, end.y))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, ofMillis(QUICK_PAUSE)));

        driver.perform(Collections.singletonList(performGesture));
    }
}

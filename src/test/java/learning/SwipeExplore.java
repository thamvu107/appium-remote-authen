package learning;

import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.Sequence;
import utils.InteractionUtils;
import utils.ScreenSizeUtils;

import java.awt.*;
import java.time.Duration;

import static devices.MobileFactory.getEmulator;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class SwipeExplore {
    private static ScreenSizeUtils screenSize;


    public static void main(String[] args) {
        AppiumDriver driver;

//        driver = DriverFactory.getMobileDriver(MobilePlatform.IOS);
//        driver = DriverFactory.getLocalServerDriver(CapabilityFactory.getCaps(getEmulator()));

        DriverProvider driverProvider = new DriverProvider();

        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);
        InteractionUtils mobileInteraction = new InteractionUtils(driver);
        screenSize = new ScreenSizeUtils(driver);
        // Swipe up before interacting
        swipeVertical();
        swipeVertical2(driver);

        driver.quit();
    }

    private static void swipeVertical() {

        int screenWidth = screenSize.getWidth();
        int screenHeight = screenSize.getHeight();


        System.out.printf("%d x %d \n", screenWidth, screenHeight);

        int x = screenWidth / 2;
        int startY = (int) (screenHeight * 0.80);
        int endY = (int) (screenHeight * 0.20);
        System.out.printf("%d x %d %d: \n", x, startY, endY);
        Point start = new Point(x, startY);
        Point end = new Point(x, endY);

        PointerInput pointerInput = new PointerInput(Kind.TOUCH, "finger1");

        Sequence sequence = new Sequence(pointerInput, 1)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, viewport(), start.x, start.y))
                .addAction(pointerInput.createPointerDown(MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, ofMillis(250)))
                .addAction(pointerInput.createPointerMove(ofMillis(250), viewport(), end.x, end.y))
                .addAction(pointerInput.createPointerUp(MouseButton.LEFT.asArg()));

    }

    private static void swipeVertical2(AppiumDriver driver) {
        Dimension windowSize = driver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        // Construct coordinators
        int startX = 50 * screenWidth / 100;
        int startY = 50 * screenHeight / 100;
        int endY = 10 * screenHeight / 100;

        System.out.printf("%d x %d %d \n", startX, startY, endY);

    }


}

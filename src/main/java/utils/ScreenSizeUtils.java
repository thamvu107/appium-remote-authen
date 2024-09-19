package utils;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.Dimension;

public class ScreenSizeUtils {
    private final AppiumDriver driver;

    @Getter
    public final Dimension dimension;

    @Getter
    private final int width;
    @Getter
    private final int height;

    public ScreenSizeUtils(AppiumDriver driver) {

        this.driver = driver;
        synchronized (driver) {
            this.dimension = driver.manage().window().getSize();
            this.width = dimension.getWidth();
            this.height = dimension.getHeight();
        }
    }
}

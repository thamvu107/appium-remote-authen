package learning.componentExploring.components;

import annotations.selector.ByAccessibilityId;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static constants.WaitConstants.SWIPE_SHORT_EXPLICIT_WAIT;

@Slf4j
@ByAccessibilityId("Carousel")
public class CarouselComponent extends Component {
    private final AppiumDriver driver;
    @Getter
    private final WebElement carouselEl;

    public CarouselComponent(AppiumDriver driver, WebElement componentElement) {
        super(driver, componentElement);
        this.driver = driver;
        this.carouselEl = componentElement;

    }

    private final By currentCardLoc = AppiumBy.accessibilityId("card");

    private final By currentSlideTextContainerLoc = AppiumBy.className("android.view.ViewGroup");
    private final By currentSlideTextLoc = AppiumBy.className("android.widget.TextView");
    //  private final By currentCardDescriptionLoc =
    @Getter
    private final By currentCardTitleLoc =
            AppiumBy.xpath("//android.view.ViewGroup[@content-desc='slideTextContainer'][1]//android.widget.TextView[1]");

    @Getter
    private final By firstCardWrapperLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"__CAROUSEL_ITEM_0_READY__\")");

    @Getter
    private final By lastCardWrapperLoc = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"__CAROUSEL_ITEM_5_READY__\")");


//  private WebElement currentCardEl() {
//    try {
//      return elementUtils.waitForFindingElement(this.carouselEl(), currentCardLoc);
//    } catch (Exception e) {
//      String message = "Could not find current card";
//      log.atError().setMessage(message).log();
//      throw new RuntimeException(message, e);
//    }
//  }

    private WebElement currentCardEl() {
        try {
//      List<WebElement> elements = elementUtils.waitForFindingElements(this.carouselEl(), currentCardLoc);
            List<WebElement> elements = elementUtils.waitForFindingElements(this.carouselEl, currentCardLoc);

            if (elements.isEmpty()) {
                String message = "Carousel is empty";
                log.atError().setMessage(message).log();
                throw new RuntimeException(message);
            }
            return elements.get(0);

        } catch (Exception e) {
            String message = "Could not find current card";
            log.atError().setMessage(message).log();
            throw new RuntimeException(message, e);
        }
    }


    private final WebElement firstCardEl() {
//    return elementUtils.waitForFindingElement(carouselEl(), firstCardWrapperLoc);
        return elementUtils.waitForFindingElement(this.carouselEl, firstCardWrapperLoc);
    }

    private WebElement currentSlideTextContainerEl() {

        return currentCardEl().findElement(currentSlideTextContainerLoc);
    }

    private List<WebElement> currentSlideTextEls() {
        return currentSlideTextContainerEl().findElements(currentSlideTextLoc);
    }

    public WebElement currentCardTitleEl() {
        return currentSlideTextEls().get(0);
    }


    public WebElement currentCardTitleElement() {
        return currentSlideTextEls().get(0);
    }

    public WebElement currentCardDescriptionEl() {

        return currentSlideTextEls().get(1);
    }


    public WebElement targetCardTitleEl(String title) {
        try {
            By targetCardTitleLoc =
                    AppiumBy.xpath("//android.widget.TextView[@text=\" " + title + "\"]");
//      return elementUtils.waitForElementToBeVisible(this.carouselEl(), targetCardTitleLoc, 10);
            return elementUtils.waitForFindingElement(this.carouselEl, targetCardTitleLoc, SWIPE_SHORT_EXPLICIT_WAIT);
        } catch (Exception e) {
            return null;
        }
    }

    private WebElement lastCardEl() {
        return elementUtils.waitForFindingElement(this.carouselEl, lastCardWrapperLoc);
    }


}

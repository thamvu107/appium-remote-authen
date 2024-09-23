package learning.poms.pom.components;

import annotations.selectors.ComponentByAccessibilityId;
import annotations.selectors.ComponentByAndroidUiAutomator2;
import annotations.selectors.ComponentByClassName;
import annotations.selectors.ComponentByCssSelector;
import annotations.selectors.ComponentById;
import annotations.selectors.ComponentByXpath;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
public class Component {

  protected AppiumDriver driver;
  protected WebElement componentEl;

  public Component(AppiumDriver driver, WebElement componentEl) {
    this.driver = driver;
//    this.componentEl = findComponent(this.getClass());
    this.componentEl = componentEl;
  }

  public Component(AppiumDriver driver) {
    this.driver = driver;
    this.componentEl = findComponent(this.getClass());
  }

  public <T extends Component> WebElement findComponent(Class<T> componentClass) {
    By componentLoc = getComponentLocator(componentClass);

    WebElement element = driver.findElement(componentLoc);

    return element;

  }


  private <T extends Component> By getComponentLocator(Class<T> componentClass) {
    //TODO improve code reusability here
    if (componentClass.isAnnotationPresent(ComponentByAccessibilityId.class)) {
      return AppiumBy.accessibilityId(componentClass.getAnnotation(ComponentByAccessibilityId.class).value());
    } else if (componentClass.isAnnotationPresent(ComponentById.class)) {
      return AppiumBy.id(componentClass.getAnnotation(ComponentById.class).value());
    } else if (componentClass.isAnnotationPresent(ComponentByClassName.class)) {
      return AppiumBy.className(componentClass.getAnnotation(ComponentByClassName.class).value());
    } else if (componentClass.isAnnotationPresent(ComponentByAndroidUiAutomator2.class)) {
      return AppiumBy.androidUIAutomator(componentClass.getAnnotation(ComponentByAndroidUiAutomator2.class).value());
    } else if (componentClass.isAnnotationPresent(ComponentByCssSelector.class)) {
      return AppiumBy.className(componentClass.getAnnotation(ComponentByClassName.class).value());
    } else if (componentClass.isAnnotationPresent(ComponentByXpath.class)) {
      return AppiumBy.xpath(componentClass.getAnnotation(ComponentByXpath.class).value());
    } else {
      String errorMessage = String.format("Component class %s must have annotation belongs %s, %s ,%s, %s",
                                          componentClass.getSimpleName(),
                                          ComponentByAccessibilityId.class.getSimpleName(),
                                          ComponentById.class.getSimpleName(),
                                          ComponentByClassName.class.getSimpleName(),
                                          ComponentByCssSelector.class.getSimpleName(),
                                          ComponentByXpath.class.getSimpleName());
      log.atError().setMessage(errorMessage).log();
      throw new IllegalArgumentException(errorMessage);
    }
  }
}

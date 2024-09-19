package utils;

import annotations.selector.ByAccessibilityId;
import annotations.selectors.ComponentByAccessibilityId;
import annotations.selectors.ComponentByClassName;
import annotations.selectors.ComponentByCssSelector;
import annotations.selectors.ComponentById;
import annotations.selectors.ComponentByXpath;
import enums.PlatformType;
import io.appium.java_client.AppiumBy;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.lang.annotation.Annotation;

@Slf4j
public class ComponentLocatorUtil {
  public By getComponentLocator(Class<?> componentClass, PlatformType platform) throws NoSuchMethodException, IllegalAccessException {
    try {
      if (componentClass.isAnnotationPresent(ComponentByAccessibilityId.class)) {
        return getLocatorByAnnotation(componentClass, platform, ComponentByAccessibilityId.class,
                                      AppiumBy::accessibilityId);
      } else if (componentClass.isAnnotationPresent(ComponentById.class)) {
        return getLocatorByAnnotation(componentClass, platform, ComponentById.class, AppiumBy::id);
      } else if (componentClass.isAnnotationPresent(ComponentByClassName.class)) {
        return getLocatorByAnnotation(componentClass, platform, ComponentByClassName.class, AppiumBy::className);

      } else if (componentClass.isAnnotationPresent(ComponentByCssSelector.class)) {
        return getLocatorByAnnotation(componentClass, platform, ComponentByCssSelector.class, AppiumBy::cssSelector);
      } else if (componentClass.isAnnotationPresent(ComponentByXpath.class)) {
        return getLocatorByAnnotation(componentClass, platform, ComponentByXpath.class, AppiumBy::xpath);
      } else {
        throw new IllegalArgumentException(
          "Component class " + componentClass.getSimpleName() + " must have annotation " +
            ByAccessibilityId.class.getSimpleName() + " or " +
            ComponentByXpath.class.getSimpleName() + " or " +
            ComponentByCssSelector.class.getSimpleName());
      }
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
  }

  private static By getLocatorByAnnotation(Class<?> componentClass, PlatformType platform,
                                           Class<? extends Annotation> annotationType, SelectorFunction function)
    throws NoSuchMethodException, IllegalAccessException {
    Annotation annotation = null;
    try {
      annotation = componentClass.getAnnotation(annotationType);
    } catch (NullPointerException e) {
      String errorMessage =
        "Component class " + componentClass.getSimpleName() + " must have annotation " + annotationType.getSimpleName();
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new NullPointerException(errorMessage);
    }
    try {
      String locatorValue = getAnnotationValue(annotation, platform.toString().toLowerCase());
      return function.apply(locatorValue);

    } catch (Exception e) {
      log.atError().setMessage(e.getMessage()).setCause(e.getCause()).log();
      throw new RuntimeException(e.getMessage(), e.getCause());
    }
  }

  private static String getAnnotationValue(Annotation annotation, String platform)
    throws NoSuchMethodException, IllegalAccessException {
    try {
      return (String) annotation.annotationType().getMethod(platform).invoke(annotation);
    } catch (Exception e) {
      log.atError().setMessage(e.getMessage()).setCause(e.getCause()).log();
      throw new RuntimeException(e.getMessage(), e.getCause());
    }
  }

  @FunctionalInterface
  private interface SelectorFunction {
    By apply(String value);
  }
}

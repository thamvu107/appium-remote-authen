package learning.componentExploring.components;

import annotations.selectors.ComponentByAccessibilityId;
import annotations.selectors.ComponentByClassName;
import annotations.selectors.ComponentByCssSelector;
import annotations.selectors.ComponentById;
import annotations.selectors.ComponentByXpath;
import enums.PlatformType;
import exceptions.ComponentConstructorException;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Require;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ElementUtils;
import utils.PlatformUtil;
import utils.WaitUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
public class Component {
  protected AppiumDriver driver;
  @Getter
  protected By componentLoc;
  @Getter
  protected WebElement componentEl;
  protected WaitUtils waitUtils;
  protected ElementUtils elementUtils;
  //  protected ComponentLocatorUtil componentLocatorUtil;
  protected PlatformType platformType;


//    public Component(AppiumDriver driver, Map<PlatformType, By> componentLocator) {
//        this.driver = driver;
//        this.waitUtils = new WaitUtils(this.driver);
//        this.platformType = new PlatformUtil(this.driver).getCurrentPlatform();
//        this.elementUtils = new ElementUtils(driver);
//        this.componentLoc = elementUtils.getLocator(componentLocator);
//        this.componentEl = elementUtils.waitForFindingElement(this.componentLoc);
//        this.componentElementUtils = new ComponentElementUtils(driver, componentEl);
//
//        checkComponentElementFound();
//    }

//    public Component(AppiumDriver driver, By componentLocator) {
//        this.driver = driver;
//        this.waitUtils = new WaitUtils(this.driver);
//        this.platformType = new PlatformUtil(this.driver).getCurrentPlatform();
//        this.elementUtils = new ElementUtils(driver);
//        this.componentLoc = componentLocator;
//        this.componentEl = elementUtils.waitForFindingElement(this.componentLoc);
//        this.componentElementUtils = new ComponentElementUtils(driver, componentEl);
//
//        checkComponentElementFound();
//    }
//
//
//    public Component(AppiumDriver driver, WebElement componentElement) {
//        this.driver = driver;
//        this.waitUtils = new WaitUtils(this.driver);
//        this.platformType = new PlatformUtil(this.driver).getCurrentPlatform();
//        this.elementUtils = new ElementUtils(driver);
//        this.componentEl = componentElement;
//        this.componentElementUtils = new ComponentElementUtils(driver, componentEl);
//
//        checkComponentElementFound();
//    }

  public Component(AppiumDriver driver, Map<PlatformType, By> componentLocator) {
    this(driver, new ElementUtils(driver).getLocator(componentLocator));
  }


  public Component(AppiumDriver driver, By componentLocator) {
    this.driver = driver;
    this.componentLoc = componentLocator;
    this.platformType = new PlatformUtil(driver).getCurrentPlatform();
    this.waitUtils = new WaitUtils(driver);
    this.elementUtils = new ElementUtils(driver);
    this.componentEl = elementUtils.waitForFindingElement(this.componentLoc);
//    this.componentElementUtils = new ComponentElementUtils(driver, componentEl);
  }

  public Component(AppiumDriver driver, WebElement componentElement) {
    this.driver = driver;
    this.componentEl = componentElement;

    this.platformType = new PlatformUtil(driver).getCurrentPlatform();
    this.waitUtils = new WaitUtils(driver);
    this.elementUtils = new ElementUtils(driver);
//    System.out.println("this.componentEl " + this.componentEl);
//    this.componentElementUtils = new ComponentElementUtils(driver, componentEl);
  }

//  public Component(AppiumDriver driver, WebElement componentEl) {
//    this.driver = driver;
//    this.componentEl = componentEl;
//    this.waitUtils = new WaitUtils(this.driver);
//    this.platformType = new PlatformUtil(this.driver).getCurrentPlatform();
//  }

  public WebElement findElement(By locator) {
    Require.nonNull("Locator", locator);

    // Narrow down searching scope
    // wait until the componentExploreing displayed
    return this.componentEl.findElement(locator);
  }

  public List<WebElement> findElements(By locator) {
    Require.nonNull("Locator", locator);
    // Narrow down searching scope
    return this.componentEl.findElements(locator);
  }

  public <T extends Component> T findComponent(Class<T> componentClass) {
    Require.nonNull("componentClass", componentClass);
    List<T> components = findComponents(componentClass);
    if (components.size() == 0) {
      log.atInfo().setMessage("componentClass " + componentClass + " not found").log();
      throw new NoSuchElementException("componentClass " + componentClass + " not found");
    }

    return components.get(0);
  }

  public <T extends Component> List<T> findComponents(Class<T> componentClass) {
    // 1. Get the component locator/selector

    By componentLoc = getComponentLocator(componentClass);
    System.out.println("componentLoc " + componentLoc);

    // 2. Find the elements and Build Constructor
    // 2.1 Find the elements
//    /Wait until the component displayed on the page
//        // In case the component is not on screen(for Android) need to swipe the screen
//        // TODO: Explore this logic - null -> swipe the screen to find the component on the screen or not ?
    waitUntilComponentsDisplayed(componentLoc);
//
//    List<WebElement> elements = findElements(componentLoc);
    List<WebElement> elements = componentEl.findElements(componentLoc);

    // 2.2 Define component class's constructor

    Constructor<T> constructor;
    try {
      constructor = getComponentConstructor(componentClass);
    } catch (ComponentConstructorException e) {
      String errorMessage = "ERR: Failed to get constructor for findComponents " + componentClass.getSimpleName() + e.getMessage();
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new RuntimeException(errorMessage, e);
    }

    // 3. Map the elements to components

    return mapElementsToComponents(elements, constructor);

  }

  //    private <T extends Component> Constructor<T> getComponentConstructor(Class<T> componentClass) throws ComponentConstructorException {
////        Class<?>[] constructorParams = new Class[]{AppiumDriver.class, By.class};
//        Class<?>[] constructorParams = new Class[]{AppiumDriver.class, WebElement.class};
//        try {
//            return componentClass.getConstructor(constructorParams);
//        } catch (NoSuchMethodException | SecurityException e) {
//            String errorMessage = String.format("[ERR] The componentExploreing must have a constructor with params %s", Arrays.toString(constructorParams));
//            log.atError().setMessage(errorMessage).setCause(e).log();
//            throw new ComponentConstructorException(errorMessage, e);
//        }
//    }
//
//
  private void waitUntilComponentsDisplayed(By componentLoc) {
    waitUtils.explicitWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(componentLoc));
    // TODO: Implement swiping logic for Android if necessary
  }


  private <T extends Component> Constructor<T> getComponentConstructor(Class<T> componentClass) throws ComponentConstructorException {
    Class<?>[] constructorParams = new Class[] {AppiumDriver.class, WebElement.class};
    try {
      return componentClass.getConstructor(constructorParams);
    } catch (NoSuchMethodException | SecurityException e) {
      String errorMessage =
        String.format("[ERR] The componentExploreing must have a constructor with params %s", Arrays.toString(constructorParams));
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new ComponentConstructorException(errorMessage, e);
    }
  }

  private <T extends Component> By getComponentLocator(Class<T> componentClass) {
    //TODO improve code reusability here
    if (componentClass.isAnnotationPresent(ComponentByAccessibilityId.class)) {
      return AppiumBy.accessibilityId(componentClass.getAnnotation(ComponentByAccessibilityId.class).value());
    } else if (componentClass.isAnnotationPresent(ComponentById.class)) {
      return AppiumBy.id(componentClass.getAnnotation(ComponentById.class).value());
    } else if (componentClass.isAnnotationPresent(ComponentByClassName.class)) {
      return AppiumBy.className(componentClass.getAnnotation(ComponentByClassName.class).value());
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


  private <T extends Component> List<T> mapElementsToComponents(List<WebElement> elements, Constructor<T> constructor) {
    // Convert all elements to components
    return elements.stream().map(webElement -> {
      try {
        return constructor.newInstance(driver, webElement);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
        String errorMessage = "Failed to create component instance";
        log.atError().setMessage(errorMessage).setCause(e).log();
        throw new RuntimeException(errorMessage, e);
      }
    }).collect(Collectors.toList());
  }
}

package learning.genericType;

import annotations.selectors.ComponentByXpath;
import learning.componentExploring.components.BottomNavComponent;
import org.testng.annotations.Test;

public class AnnotationTest {

  @Test
  public void testAnnotation() {

    printComponent(BottomNavComponent.class);
  }

  public static <T> void printComponent(Class<T> componentClass) {
    String xpath = componentClass.getAnnotation(ComponentByXpath.class).value();
    System.out.println("xpath " + xpath);
  }
}

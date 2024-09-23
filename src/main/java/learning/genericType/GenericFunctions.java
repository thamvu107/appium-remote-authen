package learning.genericType;

public class GenericFunctions {
  // A Generic method
  public static <T> void genericDisplay(T element) {
    System.out.println("Display generic: " + element.getClass().getName() + " = " + element);

  }
}

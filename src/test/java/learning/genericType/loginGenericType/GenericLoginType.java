package learning.genericType.loginGenericType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GenericLoginType {
  public <T extends LoginPage> void login(Class<T> genericLoginPageClass) {
    try {
      // Retrieve the default constructor of the class
      Constructor<T> constructor = genericLoginPageClass.getConstructor();
      // Create a new instance of the class
      T genericLoginPage = constructor.newInstance();
      // Call the login method on the instance
      genericLoginPage.login();
    } catch (NoSuchMethodException e) {
      System.err.println("No default constructor found for " + genericLoginPageClass.getName());
      e.printStackTrace();
    } catch (InstantiationException e) {
      System.err.println("Unable to instantiate " + genericLoginPageClass.getName());
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      System.err.println("Illegal access when trying to instantiate " + genericLoginPageClass.getName());
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      System.err.println("Constructor threw an exception for " + genericLoginPageClass.getName());
      e.printStackTrace();
    }
  }

}

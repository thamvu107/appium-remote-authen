package learning.componentExploring.test;

import learning.genericType.loginGenericType.LoginPage;

import java.lang.reflect.Constructor;

public class ComponentExploring {
  public static void main(String[] args) {
//    new ComponentExploring().login(InternalLoginPage.class);
//    new ComponentExploring().login(ExternalLoginPage.class);
  }

  public <T extends LoginPage> void login(Class<T> loginPageClass) {
    Class<?>[] params = new Class[] {};
    try {
      Constructor<T> constructor = loginPageClass.getConstructor(params);
      T loginPageObj = constructor.newInstance();
      loginPageObj.login();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

package learning.genericType.loginGenericType;

import org.testng.annotations.Test;

public class LoginTest {

  @Test
  public void loginInternal() {
    new GenericLoginType().login(InternalLoginPage.class);
  }

  @Test
  public void loginExternal() {
    new GenericLoginType().login(ExternalLoginPage.class);
  }
}

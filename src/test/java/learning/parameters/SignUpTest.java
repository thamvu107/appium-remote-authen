package learning.parameters;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

@Test
public class SignUpTest {

  @Parameters({"email", "password"})
  public void signUp(String email, String password) {
    System.out.println(new GregorianCalendar().getTime());
    System.out.println("email: " + email);
    System.out.println("password: " + password);
    try {
      Thread.sleep(1000);
    } catch (Exception ignored) {
    }
  }
}

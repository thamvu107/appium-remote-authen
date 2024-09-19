package learning.testNG.parameters.dataProvider.testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {

  @Parameters("configureFile")
  @BeforeClass(alwaysRun = true)
  public void beforeClass(String configureFile) {
    System.out.println("configureFile " + configureFile);
  }

  @Test
  public void testSignInValid() {
    System.out.println("signIn test valid");
  }

  @Test
  public void testSignInvalid() {
    System.out.println("signIn test invalid");
  }
}

package learning.testNG.parameters.dataProvider.testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

  @Parameters("configureFile")
  @BeforeClass(alwaysRun = true)
  public void beforeClass(String configureFile) {
    System.out.println("configureFile " + configureFile);
  }

  @Test
  public void testSignUpValid() {
    System.out.println("signUp test valid");
  }

  @Test
  public void testSignUpInvalid() {
    System.out.println("signUp test invalid");
  }
}

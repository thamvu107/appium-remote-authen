package learning.testNG.parameters.dataProvider.testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

  @Parameters("configureFile")
  @BeforeClass(alwaysRun = true)
  public void beforeClass(String configureFile) {
    System.out.println("configureFile " + configureFile);
  }

  @Test
  public void testHomePage() {
    System.out.println("Homepage test working fine");
  }

  @Test
  public void testHomePageBroken() {
    System.out.println("Test HomePage Broken");
  }
}

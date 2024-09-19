package learning.poms.pom.tests;

import base.BaseTest;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import learning.poms.pom.pages.LoginPage1;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.login.LoginScreen;

import static devices.MobileFactory.getEmulator;

public class LoginTest1 extends BaseTest {
  private LoginScreen loginScreen;

  @BeforeClass
  public void beforeClass() {
    driverProvider = new DriverProvider();
    Capabilities caps = CapabilityFactory.getCaps(getEmulator());
    driver = driverProvider.getLocalServerDriver(caps);
    loginScreen = new HomeScreen(driver).openLoginScreen();

  }

  @BeforeMethod
  public void beforeMethod() {

    loginScreen.openSignInForm();
  }

  @Test
  public void loginTest() {
    LoginPage1 loginPage = new LoginPage1(driver);

    loginPage.loginFormComponent()
      .inputEmail("testing.email@gmail.com")
      .inputPassword("yourPassword12345")
      .clickLoginBtn();

    Assert.assertTrue(loginPage.isLoginSuccess());
  }
}

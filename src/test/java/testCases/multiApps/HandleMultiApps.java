package testCases.multiApps;

import annotations.author.Author;
import base.BaseTest;
import com.google.common.base.Verify;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.setting.SettingScreen;

import static devices.MobileFactory.getEmulator;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class HandleMultiApps extends BaseTest {

  @BeforeClass
  public void beforeWebViewClass() {

    driverProvider = new DriverProvider();
//        Capabilities caps = CapabilityFactory.getCaps(getSimulator());
    Capabilities caps = CapabilityFactory.getCaps(getEmulator());
    driver = driverProvider.getLocalServerDriver(caps);
    setLogParams(caps);
  }

  @Author(THAM_VU)
  @Test
  public void openSettingApp() {
    homeScreen = new HomeScreen(driver).openHomeScreen();
    Verify.verify(homeScreen.homeScreenDisplayed(), "Home screen is not open");
    SettingScreen settingScreen = new SettingScreen(driver)
      .runAppInBackground()
      .switchSettingApp();
    Assert.assertNotNull(settingScreen.seeSettingPage());

    settingScreen.switchAppUnderTest();
    Assert.assertTrue(homeScreen.homeScreenDisplayed(), "Home screen is not open");
  }
}

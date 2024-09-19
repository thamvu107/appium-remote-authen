package testCases.swipe;

import annotations.author.Author;
import base.BaseTest;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.SwipeScreen;

import static constants.SwipeScreenConstants.MAX_SWIPES;
import static constants.SwipeScreenConstants.TARGET_CARD_TITLE_SWIPE_LEFT;
import static constants.SwipeScreenConstants.TARGET_CARD_TITLE_SWIPE_RIGHT;
import static devices.MobileFactory.getEmulator;
import static interfaces.IAuthor.ADMIN;
import static interfaces.IAuthor.DEV;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class HorizontalSwipeTest extends BaseTest {
  private SwipeScreen swipeScreen;

  @BeforeClass
  public void setupSwipeTestClass() {
    driverProvider = new DriverProvider();
    Capabilities caps = CapabilityFactory.getCaps(getEmulator());
    driver = driverProvider.getLocalServerDriver(caps);
    setLogParams(caps);
    swipeScreen = new HomeScreen(driver).goToSwipeScreen();
  }

  @BeforeMethod
  public void beforeMethod() {
    swipeScreen.goToSwipeScreen();
  }

  @AfterMethod
  public void afterMethod() {
    Assert.assertTrue(swipeScreen.goToTheFirstCard(MAX_SWIPES));
  }

  @Author(THAM_VU)
  @Test
  public void swipeLeftToTargetCard() {
    boolean isFoundTarget = swipeScreen.swipeLeftToCardTitle(TARGET_CARD_TITLE_SWIPE_LEFT, MAX_SWIPES);

    Assert.assertTrue(isFoundTarget, "This is not target card");
  }

  @Author(ADMIN)
  @Test
  public void swipeRightToTargetCard() {
    boolean isFoundTarget = swipeScreen.swipeLeft(MAX_SWIPES)
      .swipeRightToCardTitle(TARGET_CARD_TITLE_SWIPE_RIGHT, MAX_SWIPES);

    Assert.assertTrue(isFoundTarget, "This is not target card");
  }

  @Author(THAM_VU)
  @Test
  public void swipeToFirstCard() {
    boolean isFoundTarget = swipeScreen.goToTheFirstCard(MAX_SWIPES);
    Assert.assertTrue(isFoundTarget, "This is not first card");
  }

  @Author(DEV)
  @Test
  public void swipeToLastCard() {
    boolean isFoundTarget = swipeScreen.goToLastCard(MAX_SWIPES);
    Assert.assertTrue(isFoundTarget, "This is not last card");
  }
}

package entity.app;

import constants.android.IAppUnderTest;
import lombok.Getter;

@Getter
public class IOSAppUnderTest {
  private final String bundleId;

  public IOSAppUnderTest() {

    this.bundleId = IAppUnderTest.BUNDLE_ID;
  }

}

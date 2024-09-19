package entity.app;

import constants.android.IAppUnderTest;
import lombok.Getter;

import java.time.Duration;

@Getter
public class AndroidAppUnderTest {
  private final String appPackage;
  private final String appActivity;
  private final String appPath;
  private final boolean appWaitForLaunch;
  private final Duration appWaitDuration;
  private final Duration androidInstallTimeout;
  private final boolean autoGrantPermission;
  private final int remoteAppCacheLimit;
  private final boolean enforceAppInstall;

  public AndroidAppUnderTest() {
    this.appPackage = IAppUnderTest.APP_PACKAGE;
    this.appActivity = IAppUnderTest.APP_ACTIVITY;
    this.appPath = IAppUnderTest.APP_PATH;
    this.appWaitForLaunch = IAppUnderTest.APP_WAIT_FOR_LAUNCH;
    this.appWaitDuration = IAppUnderTest.APP_WAIT_DURATION;
    this.androidInstallTimeout = IAppUnderTest.ANDROID_INSTALL_TIMEOUT;
    this.autoGrantPermission = IAppUnderTest.AUTO_GRANT_PERMISSION;
    this.remoteAppCacheLimit = 0; // zero disables apps caching
    this.enforceAppInstall = IAppUnderTest.IS_ENFORCE_APP_INSTALL;
  }

}

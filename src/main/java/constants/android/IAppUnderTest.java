package constants.android;

import java.time.Duration;

public interface IAppUnderTest {
  String APP_PACKAGE = "com.wdiodemoapp";
  String APP_ACTIVITY = "com.wdiodemoapp.MainActivity";
  String APP_PATH = "path";

  boolean APP_WAIT_FOR_LAUNCH = true;
  Duration APP_WAIT_DURATION = Duration.ofMillis(400_000L); // default = 20_000

  Duration ANDROID_INSTALL_TIMEOUT = Duration.ofMillis(120_000L); // default = 90_000

  boolean AUTO_GRANT_PERMISSION = true;
  boolean IS_ENFORCE_APP_INSTALL = false;
  String BUNDLE_ID = "org.reactjs.native.example.wdiodemoapp";


//  String PLATFORM_NAME = PropertyUtils.getProperty("android.platform");
//  String PLATFORM_VERSION = PropertyUtils.getProperty("android.platform.version");
//  String APP_NAME = PropertyUtils.getProperty("android.app.name");
//  String APP_RELATIVE_PATH = PropertyUtils.getProperty("android.app.location") + APP_NAME;
//  String APP_PATH = getAbsolutePath(APP_RELATIVE_PATH);
//  String DEVICE_NAME = PropertyUtils.getProperty("android.device.name");
//  String APP_PACKAGE_NAME = PropertyUtils.getProperty("android.app.packageName");
//  String APP_ACTIVITY_NAME = PropertyUtils.getProperty("android.app.activityName");
//  String APP_FULL_RESET = PropertyUtils.getProperty("android.app.full.reset");
//  String APP_NO_RESET = PropertyUtils.getProperty("android.app.no.reset");


}

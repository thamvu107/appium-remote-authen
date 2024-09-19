package constants;

import java.time.Duration;

import static java.time.Duration.ofMillis;

public interface IAndroidMobileData {
    String AVD_PLATFORM_VERSION = "13.0";

    String SMALL_AVD_DEVICE_NAME = "Small Phone API 33";
    String SMALL_AVD = "Small_Phone_API_33_1";
    int AVD_SYSTEMPORT = 8200;
    int SYSTEMPORT = 8300;

    String AVD_DEVICE_NAME = "Pixel 5 API 33";
    String AVD = "Pixel_5_API_33_1";
    String PIXEL_4_DEVICE_NAME = "Pixel 4 API 34";
    String PIXEL_4_AVD = "Pixel_4_API_34";
    Duration AVD_READY_TIMEOUT = ofMillis(500_000L);
    Duration AVD_LAUNCH_TIMEOUT = ofMillis(500_000L);
    Duration APP_WAIT_DURATION = ofMillis(500_000L);
    String NETWORK_SPEED = "full"; /// gsm - hscsd - gprs - edge - umts - hsdpa - lte - evdo - full
    boolean IS_HEADLESS = false; // default = false -> set true to run on device remote
    String ANDROID_MOBILE_UUID = "192.168.1.6:5555";
    String ANDROID_MOBILE_NAME = "Standard_PC__i440FX___PIIX__1996_";
    String TABLET_UUID = "192.168.1.7:5555";
    String TABLET_NAME = "Standard PC (i440FX + PIIX, 1996)";

}

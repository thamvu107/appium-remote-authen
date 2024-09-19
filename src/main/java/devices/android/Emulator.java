package devices.android;

import constants.IAndroidMobileData;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class Emulator extends AndroidMobile {
  private String avd;
  private Duration avdReadyTimeout;
  private Duration avdLaunchTimeout;
  private Duration appWaitDuration;
  private String netWorkSpeed;
  private boolean isHeadless;
  private String avdArgs;

  //    public Emulator(String deviceName, String platformVersion, String avd) {
//        super(deviceName, platformVersion);
//        this.avd = avd;
//        this.avdReadyTimeout = IAndroidMobileData.AVD_READY_TIMEOUT;
//        this.avdLaunchTimeout = IAndroidMobileData.AVD_LAUNCH_TIMEOUT;
//        this.appWaitDuration = IAndroidMobileData.APP_WAIT_DURATION;
//        this.netWorkSpeed = IAndroidMobileData.NETWORK_SPEED;
//        this.isHeadless = IAndroidMobileData.IS_HEADLESS;
//        this.avdArgs = "-netspeed edge";//"-wipe-data";
//    }
  public Emulator(String deviceName, String platformVersion, String avd) {
    super(deviceName, platformVersion);

    this.avd = avd;
    this.avdReadyTimeout = IAndroidMobileData.AVD_READY_TIMEOUT;
    this.avdLaunchTimeout = IAndroidMobileData.AVD_LAUNCH_TIMEOUT;
    this.appWaitDuration = IAndroidMobileData.APP_WAIT_DURATION;
    this.netWorkSpeed = IAndroidMobileData.NETWORK_SPEED;
    this.isHeadless = IAndroidMobileData.IS_HEADLESS;
    this.avdArgs = "-netspeed edge";//"-wipe-data";
  }

  public Emulator(String deviceName, String platformVersion, String adv, int systemPort) {
    this(deviceName, platformVersion, adv);
    this.systemPort = systemPort;
  }

  public Emulator(String udid, int systemPort) {
    super(udid, systemPort);
  }

  public Emulator setAvdTimeout(Duration timeout) {
    this.avdReadyTimeout = timeout;
    return this;
  }
}

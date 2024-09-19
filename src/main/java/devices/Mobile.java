package devices;

import enums.PlatformType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Mobile {
    protected PlatformType platformName;
    protected String deviceName;
    protected String platformVersion;
    protected String udid;


    protected Mobile(PlatformType platformName) {
        this.platformName = platformName;
    }

    protected Mobile(PlatformType platformName, String udid) {
        this(platformName);
        this.udid = udid;
    }

    protected Mobile(PlatformType platformName, String deviceName, String platformVersion) {
        this(platformName);
        this.deviceName = deviceName;
        this.platformVersion = platformVersion;
    }

    public Mobile(PlatformType platformName, String udid, String deviceName, String platformVersion) {
        this(platformName, deviceName, platformVersion);
        this.udid = udid;
    }
}

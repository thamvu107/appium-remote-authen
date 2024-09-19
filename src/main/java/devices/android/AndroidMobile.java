package devices.android;

import devices.Mobile;
import enums.PlatformType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AndroidMobile extends Mobile {

    // random between 8200 and 8299
    protected int systemPort;

    protected AndroidMobile() {
        super(PlatformType.ANDROID);
    }

    protected AndroidMobile(String deviceName, String platformVersion) {
        super(PlatformType.ANDROID, deviceName, platformVersion);

    }

    protected AndroidMobile(String deviceName, String platformVersion, int systemPort) {
        super(PlatformType.ANDROID, deviceName, platformVersion);
        this.systemPort = systemPort;
    }

    protected AndroidMobile(String udid, int systemPort) {
        super(PlatformType.ANDROID, udid);
        this.systemPort = systemPort;
    }

    protected AndroidMobile(String udid, String deviceName, String platformVersion, int systemPort) {
        super(PlatformType.ANDROID, udid, deviceName, platformVersion);
        this.systemPort = systemPort;
    }
}
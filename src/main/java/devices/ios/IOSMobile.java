package devices.ios;

import devices.Mobile;
import enums.PlatformType;
import lombok.Getter;

@Getter
public abstract class IOSMobile extends Mobile {

    protected int wdaLocalPort = 8100;

    protected IOSMobile() {
        super(PlatformType.IOS);
    }

    protected IOSMobile(String udid) {
        super(PlatformType.IOS, udid);
    }

    protected IOSMobile(String udid, int wdaLocalPort) {
        this(udid);
        this.wdaLocalPort = wdaLocalPort;
    }


    protected IOSMobile(String deviceName, String platformVersion, int wdaLocalPort) {
        super(PlatformType.IOS, deviceName, platformVersion);
        this.wdaLocalPort = wdaLocalPort;
    }

    protected IOSMobile(String udid, String deviceName, String platformVersion, int wdaLocalPort) {
        super(PlatformType.IOS, udid, deviceName, platformVersion);
        this.wdaLocalPort = wdaLocalPort;
    }
}

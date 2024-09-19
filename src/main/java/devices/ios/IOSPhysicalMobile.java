package devices.ios;

public class IOSPhysicalMobile extends IOSMobile {
    public IOSPhysicalMobile(String udid) {
        super(udid);
    }

    public IOSPhysicalMobile(String udid, int wdaLocalPort) {
        super(udid, wdaLocalPort);
    }

    public IOSPhysicalMobile(String deviceName, String platformVersion, int wdaLocalPort) {
        super(deviceName, platformVersion, wdaLocalPort);
    }


    public IOSPhysicalMobile(String udid, String deviceName, String platformVersion, int wdaLocalPort) {
        super(udid, deviceName, platformVersion, wdaLocalPort);
    }
}

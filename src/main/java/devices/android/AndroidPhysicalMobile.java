package devices.android;

public class AndroidPhysicalMobile extends AndroidMobile {
    public AndroidPhysicalMobile(String udid) {
        super();
        this.udid = udid;
    }

    public AndroidPhysicalMobile(String udid, int systemPort) {
        super(udid, systemPort);
    }

    public AndroidPhysicalMobile(String deviceName, String platformVersion, int systemPort) {
        super(deviceName, platformVersion, systemPort);
    }

    public AndroidPhysicalMobile(String udid, String deviceName, String platformVersion, int systemPort) {
        super(udid, deviceName, platformVersion, systemPort);
    }


}

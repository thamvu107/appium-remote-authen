package devices.ios;

public class Simulator extends IOSMobile {
    public Simulator(String udid) {
        super(udid);
    }

    public Simulator(String udid, int wdaLocalPort) {
        super(udid, wdaLocalPort);
    }

    public Simulator(String deviceName, String platformVersion, int wdaLocalPort) {
        super(deviceName, platformVersion, wdaLocalPort);
    }

    public Simulator(String udid, String deviceName, String platformVersion, int wdaLocalPort) {
        super(udid, deviceName, platformVersion, wdaLocalPort);
    }
}

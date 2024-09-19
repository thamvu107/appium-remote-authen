package driverFactory.capabilities;

import constants.ios.IOSAppSetting;
import devices.ios.IOSPhysicalMobile;
import devices.ios.Simulator;
import entity.app.IOSAppUnderTest;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.time.Duration;

public class IOSCapabilities extends XCUITestOptions implements IOSAppSetting {
    static IOSAppUnderTest app = new IOSAppUnderTest();

    public static XCUITestOptions getSimulatorCaps(Simulator mobile) {

        // TODO: handle to read caps value from config file
        XCUITestOptions caps = new XCUITestOptions()
                .setDeviceName(mobile.getDeviceName())
                .setUdid(mobile.getUdid())
                .setPlatformVersion(mobile.getPlatformVersion())
                .setBundleId(BUNDLEID)
//                .setApp(app.getAppPath())
                .setEnforceAppInstall(true) // default:false
                .setWdaLaunchTimeout(Duration.ofSeconds(90)) // wdaLaunchTimeout
                .setFullReset(false)
                .setNoReset(false)
                .setUseNewWDA(true)
//                .setWdaLocalPort(mobile.getWdaLocalPort())
                .setShowXcodeLog(true)
                .setPrintPageSourceOnFindFailure(true)
                .setLaunchWithIdb(true)
                .clearSystemFiles();
        caps.setCapability("--session-override", true);

//        System.out.println("caps.getApp() " + caps.getApp());

        return caps;
    }

    public static XCUITestOptions getRealMobileCaps(IOSPhysicalMobile mobile) {

        XCUITestOptions caps = new XCUITestOptions()
                .setDeviceName(mobile.getDeviceName())
                .setUdid(mobile.getUdid())
                .setBundleId(BUNDLEID)
                .clearSystemFiles();
        caps.setCapability("--session-override", true);


        return caps;
    }

}

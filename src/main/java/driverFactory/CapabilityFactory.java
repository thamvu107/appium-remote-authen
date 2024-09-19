package driverFactory;

import devices.Mobile;
import devices.android.AndroidPhysicalMobile;
import devices.android.Emulator;
import devices.ios.IOSPhysicalMobile;
import devices.ios.Simulator;
import exceptions.PlatformNotSupportException;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.options.BaseOptions;

import static driverFactory.capabilities.AndroidCapabilities.getEmulatorCaps;
import static driverFactory.capabilities.AndroidCapabilities.getRealMobileCaps;
import static driverFactory.capabilities.IOSCapabilities.getRealMobileCaps;
import static driverFactory.capabilities.IOSCapabilities.getSimulatorCaps;

public class CapabilityFactory {

    public static BaseOptions<?> getCaps(Mobile mobile) {

        if (mobile == null || mobile.getPlatformName() == null) {
            throw new IllegalArgumentException("Mobile device or platform name must not be null");
        }
        switch (mobile.getPlatformName()) {
            case ANDROID:
                return getAndroidCaps(mobile);
            case IOS:
                return getIOSCaps(mobile);
            default:
                throw new PlatformNotSupportException("Platform " + mobile.getPlatformName() + " is not supported");
        }
    }

    public static UiAutomator2Options getAndroidCaps(Mobile mobile) {

        if (mobile instanceof Emulator) {
            return getEmulatorCaps((Emulator) mobile);
        } else if (mobile instanceof AndroidPhysicalMobile) {
            return getRealMobileCaps((AndroidPhysicalMobile) mobile);
        } else {
            throw new IllegalArgumentException("Unsupported Android mobile type");
        }
    }

    public static XCUITestOptions getIOSCaps(Mobile mobile) {

        if (mobile instanceof Simulator) {
            return getSimulatorCaps((Simulator) mobile);
        } else if (mobile instanceof IOSPhysicalMobile) {
            return getRealMobileCaps((IOSPhysicalMobile) mobile);
        } else {
            throw new IllegalArgumentException("Unsupported iOS mobile type");
        }
    }


//    private static UiAutomator2Options getEmulatorCaps(Emulator emulator) {
//
//        UiAutomator2Options options = new UiAutomator2Options()
//                .setDeviceName(emulator.getDeviceName())
//                .setPlatformVersion(emulator.getPlatformVersion())
//                .setUdid(emulator.getUdid())
//                .setAvd(emulator.getAdv())
//                .setAvdLaunchTimeout(emulator.getAdvTimeout())
//                .setAvdReadyTimeout(Duration.ofMillis(240_000L))
//                .setApp(APP_PATH)
//                .setAppPackage(APP_PACKAGE)
//                .setAppActivity(APP_ACTIVITY)
//                .setAppWaitForLaunch(APP_WAIT_FOR_LAUNCH)
//                .setAppWaitDuration(APP_WAIT_FOR_LAUNCH_TIME)
//                .setUiautomator2ServerLaunchTimeout(UIAUTOMATOR2_SERVER_LAUNCH_TIMEOUT)
//                .setUiautomator2ServerInstallTimeout(UIAUTOMATOR2_SERVER_INSTALL_TIMEOUT)
//                .setUiautomator2ServerReadTimeout(UIAUTOMATOR2_SERVER_READY_TIMEOUT)
//                .setNewCommandTimeout(NEW_COMMAND_TIMEOUT)
//                .setFullReset(false)
//                .setNoReset(false)
//                .clearDeviceLogsOnStart();
//        options.setCapability("clearSystemFiles", true);
//
//        return options;
//    }


}

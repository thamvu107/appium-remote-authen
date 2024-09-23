package learning;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LaunchingEmulator {
    private static String sdkPath;// = $ANDROID_HOME; // dkPath" variable to your SDK installation directory.
    private static String adbPath;//= sdkPath + "platform-tools" + File.separator + "adb";
    private static String emulatorPath;// = sdkPath + "tools" + File.separator + "emulator";

    public static void main(String[] args) {
        sdkPath = System.getenv("ANDROID_HOME");
        System.out.println("sdkPath " + sdkPath);

        adbPath = sdkPath + "platform-tools" + File.separator + "adb";
//        emulatorPath = sdkPath + File.separator + "tools" + File.separator + "emulator";
        emulatorPath = sdkPath + File.separator + "emulator" + File.separator + "emulator";
        System.out.println("emulatorPath " + emulatorPath);
        launchEmulator("Pixel_4_API_33");

    }

    /**
     * Starts an emulator for the provided AVD name     *
     *
     * @param nameOfAVD
     */
    public static void launchEmulator(String nameOfAVD) {
        System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
        String[] aCommand = new String[]{emulatorPath, "-avd", nameOfAVD};
        try {
            Process process = new ProcessBuilder(aCommand).start();
            process.waitFor(180, TimeUnit.SECONDS);
            System.out.println("Emulator launched successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

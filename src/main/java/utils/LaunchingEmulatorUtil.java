package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class LaunchingEmulatorUtil {
    private static final String SDK_PATH = System.getenv("ANDROID_HOME");
    private static final String ADB_PATH = SDK_PATH + File.separator + "platform-tools" + File.separator + "adb";
    private static final String EMULATOR_PATH = SDK_PATH + File.separator + "emulator" + File.separator + "emulator";

    /**
     * Starts an emulator for the provided AVD name     *
     *
     * @param nameOfAVD
     */
//    public static void launchEmulator(String nameOfAVD) {
//        System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
//        String[] aCommand = new String[]{EMULATOR_PATH, "-avd", nameOfAVD};
//        try {
//            Process process = new ProcessBuilder(aCommand).start();
//            process.waitFor(30, TimeUnit.SECONDS);
//            System.out.println("Emulator launched successfully!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void launchEmulator(String nameOfAVD) {
        System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
        String[] aCommand = new String[]{EMULATOR_PATH, "-avd", nameOfAVD};
        try {
            Process process = new ProcessBuilder(aCommand).start();
            process.waitFor(30, TimeUnit.SECONDS);
            System.out.println("Emulator process started, waiting for device to be ready...");

            boolean isDeviceReady = waitForDeviceReady();
            if (isDeviceReady) {
                System.out.println("Emulator launched and device is ready!");
            } else {
                System.out.println("Emulator launched but device is not ready in time.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void killEmulator() {
        String[] aCommand = new String[]{ADB_PATH, "emu", "kill"};
        try {
            Process process = new ProcessBuilder(aCommand).start();
            process.waitFor(30, TimeUnit.SECONDS);
            System.out.println("Emulator killed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean waitForDeviceReady() throws IOException, InterruptedException {
        String[] adbCommand = new String[]{ADB_PATH, "devices"};
        boolean deviceReady = false;
        int maxAttempts = 30;
        int attempts = 0;

        while (attempts < maxAttempts && !deviceReady) {
            Process adbProcess = new ProcessBuilder(adbCommand).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(adbProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("device") && !line.contains("offline") && !line.contains("unauthorized")) {
                    deviceReady = true;
                    break;
                }
            }
            reader.close();
            adbProcess.waitFor();

            if (!deviceReady) {
                System.out.println("Device not ready, retrying...");
                TimeUnit.SECONDS.sleep(10);  // Wait for 10 seconds before retrying
            }
            attempts++;
        }
        return deviceReady;
    }
}

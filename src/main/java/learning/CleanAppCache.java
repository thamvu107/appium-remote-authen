package learning;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CleanAppCache {
    public static void main(String[] args) {
        String serialNumber = "emulator-5554";

        clearCache(serialNumber);
    }


    public static void clearCache(String serialNumber) {
        ProcessBuilder processBuilder = new ProcessBuilder("adb", "-s", serialNumber, "shell", "wipe", "cache");
        processBuilder.redirectErrorStream(true);  // Combine stdout and stderr

        try {
            Process clearDataProcess = processBuilder.start();

            // Capture the command output
            BufferedReader reader = new BufferedReader(new InputStreamReader(clearDataProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Log or handle output
            }

            // Wait for the process to complete
            int exitCode = clearDataProcess.waitFor();
            if (exitCode == 0) {
                System.out.println("Cache cleared successfully.");
            } else {
                System.err.println("Failed to clear cache. Exit code: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();  // Handle exceptions
        }
    }


}


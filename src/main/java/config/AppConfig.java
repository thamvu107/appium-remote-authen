package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
  private static final Properties properties = new Properties();

  static {
    try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("appName.properties")) {
      if (input == null) {
        throw new IOException("Unable to find appName.properties");
      }
      properties.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static String getAndroidAppFileName() {
    return properties.getProperty("androidAppFileName");
  }

  public static String getIosAppFileName() {
    return properties.getProperty("iosAppFileName");
  }
}

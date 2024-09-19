package utils;

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationDriver {


  private ValidationDriver() {
    // Private constructor to prevent instantiation
  }

  public static void validateDriverNotNull(AppiumDriver driver) {
    if (driver == null) {
      log.atError().setMessage("Driver must not be null.").log();
      throw new IllegalArgumentException("Driver must not be null.");
    }
  }

}

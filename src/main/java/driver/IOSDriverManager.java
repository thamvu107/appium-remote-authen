package driver;

import capabilities.IOSCapabilitiesManager;
import entity.ServerConfig;
import enums.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import utils.propertyReader.PropertiesMap;

import java.net.URL;

@Slf4j
public class IOSDriverManager extends AppiumDriverManager {
//  @Override
//  public AppiumDriver createDriver(URL serverURL, DeviceType deviceType, String configureFile) {
////
////    System.out.println("serverURL: " + serverURL);
////    System.out.println("deviceType: " + deviceType);
////    System.out.println("configureFile: " + configureFile);
//
//    PropertiesMap propertiesMap = getPropertiesMap(configureFile);
//
//    XCUITestOptions caps = null;
//
//    switch (deviceType) {
//      case EMULATOR:
//        caps = IOSCapabilitiesManager.getSimulatorCaps(propertiesMap);
//        break;
//      case REAL:
//        caps = IOSCapabilitiesManager.getRealIosMobileCaps(propertiesMap);
//        break;
//      default:
//        throw new IllegalStateException("Unexpected device type: " + deviceType);
//    }
//
//    if (caps != null) {
//      try {
//        return new AndroidDriver(serverURL, caps);
//      } catch (Exception e) {
//        log.error("Failed to create driver: {}", e.getMessage(), e);
////        e.printStackTrace();
//        throw new RuntimeException("Failed to create driver: ", e);
//      }
//    } else {
//      throw new IllegalArgumentException("Capabilities is null");
//    }
//  }

  @Override
  public AppiumDriver createDriver(ServerConfig serverConfig, DeviceType deviceType, String ConfigureFile) {
    return null;
  }

  private PropertiesMap getPropertiesMap(String configureFile) {
    PropertiesMap propertiesMap;

    try {
      propertiesMap = new PropertiesMap(configureFile);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load properties from file: " + configureFile, e);
    }
    return propertiesMap;
  }

  private void setLogParams(String fileName) {
    MDC.put("deviceName", fileName);
  }

  private String convertDeviceName(String deviceName) {
    System.out.println(deviceName);
    return deviceName.replaceAll(" ", "_");
  }
}

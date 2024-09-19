package driver;

import capabilities.AndroidCapabilitiesManager;
import entity.ServerConfig;
import entity.authen.RemoteServerAuthentication;
import enums.DeviceType;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.slf4j.MDC;
import utils.ServerURLUtil;
import utils.propertyReader.PropertiesMap;

import java.io.IOException;
import java.net.URL;

@Slf4j
public class AndroidDriverManager extends AppiumDriverManager {
//  @Override
//  public AppiumDriver createDriver(URL serverURL, DeviceType deviceType, String configureFile) {
//    PropertiesMap propertiesMap = getPropertiesMap(configureFile);
//
//    UiAutomator2Options caps = null;
//    switch (deviceType) {
//      case EMULATOR:
//        caps = AndroidCapabilitiesManager.getEmulatorCaps(propertiesMap);
//        break;
//      case REAL:
//        caps = AndroidCapabilitiesManager.getRealMobileCaps(propertiesMap);
//        setLogParams(caps.getCapability("udid").toString());
//        break;
//      default:
//        throw new IllegalStateException("Unexpected device type: " + deviceType);
//    }
//
//    AppiumDriver appiumDriver = null;
//
//    if (caps == null) {
//      throw new IllegalArgumentException("Capabilities is null");
//    }
//    try {
//      appiumDriver = new AndroidDriver(serverURL, caps);
//
//    } catch (SessionNotCreatedException ex) {
//
//      if (ex.getMessage().contains("Possible causes are invalid address of the remote server or browser start-up failure.")) {
//        System.out.println("Server URL is: " + serverURL + " double check the URL address or isRemote parameter");
//        throw new RuntimeException(ex.getCause());
//      } else if (ex.getMessage().contains("Appium Settings app is not running after 30000ms") &&
//        (DeviceType.EMULATOR).equals(deviceType)) {
//        appiumDriver = new AndroidDriver(serverURL, caps);
//      } else {
//        log.error("Failed to create driver: {}", ex.getMessage(), ex);
//        throw new RuntimeException(ex);
//      }
//    } catch (Exception e) {
//      log.error("Failed to create driver: {}", e.getMessage(), e);
////        e.printStackTrace();
//      throw new RuntimeException("Failed to create driver", e);
//    }
//
//    return appiumDriver;
//  }

  @Override
  public AppiumDriver createDriver(ServerConfig serverConfig, DeviceType deviceType, String configureFile) {

    UiAutomator2Options caps = AndroidCapabilitiesManager.getCaps(deviceType, configureFile);

    return getAndroidDriver(serverConfig, caps);

  }

  private static AndroidDriver getAndroidDriver(ServerConfig serverConfig, UiAutomator2Options caps) {
    URL serverURL = serverConfig.getServerURL();
    boolean isAuthenticationRequired = serverConfig.isAuthenticationRequired();

    AppiumClientConfig appiumClientConfig;

    if (isAuthenticationRequired) {
      RemoteServerAuthentication authentication = ServerURLUtil.getRemoteServerAuthentication();

      appiumClientConfig = AppiumClientConfig.defaultConfig()
        .baseUrl(serverURL)
        .authenticateAs(new UsernameAndPassword(authentication.getUsername(), authentication.getPassword()));
    } else {
      appiumClientConfig = AppiumClientConfig.defaultConfig().baseUrl(serverURL);
    }

    try {
      return new AndroidDriver(appiumClientConfig, caps);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static AppiumDriver getAndroidDriver(URL serverURL, UiAutomator2Options caps) {
    try {
      return new AndroidDriver(serverURL, caps);

    } catch (Exception e) {
      log.error("Failed to create driver: ", e.getMessage(), e);
      throw new RuntimeException("Failed to create driver", e);
    }
  }

  private static AppiumDriver getAndroidDriver(URL serverURL, DeviceType deviceType, UiAutomator2Options caps) {
    try {
      return new AndroidDriver(serverURL, caps);

    } catch (SessionNotCreatedException ex) {
      // Emulator is still running
      if (ex.getMessage().contains("Appium Settings app is not running after 30000ms") && (DeviceType.EMULATOR).equals(
        deviceType)) {
        return new AndroidDriver(serverURL, caps);
      } else {
        log.error("Session Not Created Exception", ex.getMessage(), ex);
        throw new RuntimeException(ex);
      }
    } catch (Exception e) {
      log.error("Failed to create driver: ", e.getMessage(), e);
      throw new RuntimeException("Failed to create driver", e);
    }
  }


  private static AndroidDriver getAndroidDriver(URL serverURL, RemoteServerAuthentication authentication,
                                                UiAutomator2Options caps) {

    AppiumClientConfig appiumClientConfig = AppiumClientConfig.defaultConfig()
      .baseUrl(serverURL)
      .authenticateAs(new UsernameAndPassword(authentication.getUsername(), authentication.getPassword()));

    try {
      return new AndroidDriver(appiumClientConfig, caps);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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

package driverFactory;

import constants.filePaths.jsonFiles.ServerConfigPathConstants;
import entity.ServerConfig;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import utils.DataObjectBuilderUtil;

import java.net.URL;
import java.nio.file.Path;

@Slf4j
public class DriverProvider {

  public AppiumDriver getLocalServerDriver(Capabilities caps) {

    URL localServerURL = getLocalServerURL();
    try {
      return createDriver(localServerURL, caps);
    } catch (Exception e) {
      log.atInfo().setMessage("Innit local server driver is failed").setCause(e).log();
      throw new RuntimeException(e);
    }
  }

  public AppiumDriver getRemoteServerDriver(Capabilities caps) {

    URL remoteServerURL = getRemoteServerURL();
    try {
      return createDriver(remoteServerURL, caps);
    } catch (Exception e) {
      log.atInfo().setMessage("Innit remote server driver is failed").setCause(e).log();
      throw new RuntimeException(e);
    }
  }

  private URL getLocalServerURL() {

    Path serverConfigurePath = Path.of(ServerConfigPathConstants.LOCAL_SERVER_CONFIG_JSON);
    ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);
    try {
      return serverConfig.getServerURL();
    } catch (Exception e) {
      log.atInfo().setMessage("Invalid server URL").setCause(e).log();
      throw new RuntimeException("Invalid server URL" + e);
    }
  }

  private URL getRemoteServerURL() {

    Path serverConfigurePath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_CONFIG_JSON);
    ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);

    try {
      return serverConfig.getServerURL();
    } catch (Exception e) {
      log.atInfo().setMessage("Invalid server URL").setCause(e).log();
      throw new RuntimeException("Invalid server URL" + e);

    }
  }

  private AppiumDriver createDriver(URL serverURL, Capabilities caps) {
    DriverFactory factory = new DriverFactory(serverURL, caps);
    try {
      return factory.getDriver();
    } catch (Exception e) {
      log.atInfo().setMessage("Innit driver is failed").setCause(e).log();
      throw new RuntimeException(e);
    }
  }

  public void quitDriver(AppiumDriver driver) {
    if (driver != null) {
      try {
        System.out.println(driver);
        driver.quit();
      } catch (Exception e) {
        log.atInfo().setMessage("Failed to quit driver").setCause(e).log();
        throw new RuntimeException(e);
      }
    }
  }

}

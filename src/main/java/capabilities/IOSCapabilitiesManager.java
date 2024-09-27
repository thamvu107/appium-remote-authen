package capabilities;

import io.appium.java_client.ios.options.XCUITestOptions;
import utils.propertyReader.PropertiesMap;

import java.io.File;
import java.util.Objects;

import static java.time.Duration.ofSeconds;

public class IOSCapabilitiesManager {

  private String configureFile;

  public IOSCapabilitiesManager(String configureFile) {
    this.configureFile = configureFile;
  }

  public XCUITestOptions getSimulatorCaps(PropertiesMap deviceProps) {
//    PropertiesUtils deviceProps = new PropertiesUtils(deviceCapConfig);

    XCUITestOptions caps = new XCUITestOptions()
      .setPlatformVersion(deviceProps.getProperty("platformVersion"))
      .setUdid(deviceProps.getProperty("udid"))
      .setWdaLocalPort(deviceProps.getIntProperty("wdaLocalPort"))
      .setMjpegServerPort(deviceProps.getIntProperty("mjpegServerPort"))
//      .setMjpegScreenshotUrl(deviceProps.getProperty("mjpegScreenshotUrl"))
      .setPrintPageSourceOnFindFailure(deviceProps.getBooleanProperty("printPageSourceOnFindFailure"))
//      .setApp(Objects.requireNonNull(
//          IOSCapabilitiesManager.class.getClassLoader().getResource("apps" + File.separator + deviceProps.getProperty("app")))
//                .getPath())
      .setBundleId(deviceProps.getProperty("bundleId"))
      .setFullReset(deviceProps.getBooleanProperty("fullReset"))
      .setNoReset(deviceProps.getBooleanProperty("noReset"))
      .setWdaLaunchTimeout(ofSeconds(deviceProps.getLongProperty("wdaLaunchTimeout")))
//      .setUseNewWDA(deviceProps.getBooleanProperty("useNewWDA"))
//      .setUsePrebuiltWda(deviceProps.getBooleanProperty("usePrebuiltWda"))
      .setShowXcodeLog(deviceProps.getBooleanProperty("showXcodeLog"))
      .setPrintPageSourceOnFindFailure(deviceProps.getBooleanProperty("printPageSourceOnFindFailure"))
      .setLaunchWithIdb(deviceProps.getBooleanProperty("launchWithIdb"))
      .setAutoAcceptAlerts(deviceProps.getBooleanProperty("autoAcceptAlerts"))
      .setAutoDismissAlerts(deviceProps.getBooleanProperty("autoDismissAlerts"))
      .setIsHeadless(deviceProps.getBooleanProperty("isHeadless"))
      .clearSystemFiles();
    return caps;
  }

  public XCUITestOptions getRealIosMobileCaps(PropertiesMap deviceProps) {
    XCUITestOptions caps = new XCUITestOptions()
      .setPlatformVersion(deviceProps.getProperty("platformVersion"))
      .setUdid(deviceProps.getProperty("udid"))
      .setWdaLocalPort(deviceProps.getIntProperty("wdaLocalPort"))
      .setMjpegServerPort(deviceProps.getIntProperty("mjpegServerPort"))
//      .setMjpegScreenshotUrl(deviceProps.getProperty("mjpegScreenshotUrl"))
      .setPrintPageSourceOnFindFailure(deviceProps.getBooleanProperty("printPageSourceOnFindFailure"))
//      .setApp(Objects.requireNonNull(
//          IOSCapabilitiesManager.class.getClassLoader().getResource("apps" + File.separator + deviceProps.getProperty("app")))
//                .getPath())
      //.setBundleId(deviceProps.getProperty("bundleId"))
      .setFullReset(deviceProps.getBooleanProperty("fullReset"))
      .setNoReset(deviceProps.getBooleanProperty("noReset"))
      .setWdaLaunchTimeout(ofSeconds(deviceProps.getLongProperty("wdaLaunchTimeout")))
      .setUseNewWDA(deviceProps.getBooleanProperty("useNewWDA"))
      .setUsePrebuiltWda(deviceProps.getBooleanProperty("usePrebuiltWDA"))
      .setShowXcodeLog(deviceProps.getBooleanProperty("showXcodeLog"))
      .setPrintPageSourceOnFindFailure(deviceProps.getBooleanProperty("printPageSourceOnFindFailure"))
      .setLaunchWithIdb(deviceProps.getBooleanProperty("launchWithIdb"))
      .setAutoAcceptAlerts(deviceProps.getBooleanProperty("autoAcceptAlerts"))
      .setAutoDismissAlerts(deviceProps.getBooleanProperty("autoDismissAlerts"))
      .clearSystemFiles();
    return caps;
  }

}

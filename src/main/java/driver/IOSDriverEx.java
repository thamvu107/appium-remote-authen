package driver;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;

import java.net.URL;

public class IOSDriverEx extends IOSDriver {


  public IOSDriverEx(URL remoteAddress, Capabilities capabilities) {
    super(remoteAddress, capabilities);
  }

}

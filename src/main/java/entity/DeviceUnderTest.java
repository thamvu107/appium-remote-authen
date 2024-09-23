package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
public class DeviceUnderTest {
  private  String deviceName;
  private  String platformVersion;
  private  String configureFile;

  @Override
  public String toString() {
    return "DeviceUnderTest{"
      + " deviceName='" + deviceName + '\''
      + " platformVersion='" + platformVersion + '\''
      + " configureFile='" + configureFile + '\''
      + '}';
  }
}

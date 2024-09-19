package entity.deviceConfigure;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class DeviceUnderTest {
  @NonNull
  private final String id;
  @NonNull
  private final String platformType;
  @NonNull
  private final String deviceType;
  @NonNull
  private final String configureFile;

  @Override
  public String toString() {
    return "DeviceUnderTest{"
      + "id='" + id + '\''
      + ", platformType='" + platformType + '\''
      + ", deviceType='" + deviceType + '\''
      + ", configureFile='" + configureFile + '\''
      + '}';
  }
}

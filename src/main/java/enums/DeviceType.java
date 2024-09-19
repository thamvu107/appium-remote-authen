package enums;

import lombok.Getter;

@Getter
public enum DeviceType {
  EMULATOR("emulator"),
  REAL("real"),
  SIMULATOR("simulators");

  private final String deviceType;

  DeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public static DeviceType fromString(String deviceType) {
    try {
      return DeviceType.valueOf(deviceType.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Unknown device type: " + deviceType, e);
    }
  }
}

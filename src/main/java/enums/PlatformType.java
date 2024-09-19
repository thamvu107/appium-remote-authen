package enums;

import lombok.Getter;

public enum PlatformType {
  ANDROID("android"),
  IOS("ios");

  @Getter
  private final String platformName;

  PlatformType(String platformName) {
    this.platformName = platformName;
  }

  public static PlatformType fromString(String platformName) {
    try {
      return PlatformType.valueOf(platformName.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Unknown platform: " + platformName, e);
    }
  }
}

package config.factory;

import config.MobileRunModeConfig;
import org.aeonbits.owner.ConfigCache;

public class ConfigFactory {

  private ConfigFactory() {

  }

  public static MobileRunModeConfig getConfig() {
    return ConfigCache.getOrCreate(MobileRunModeConfig.class);
  }
}

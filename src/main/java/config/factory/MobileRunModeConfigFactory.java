package config.factory;

import config.MobileRunModeConfig;
import org.aeonbits.owner.ConfigCache;

public class MobileRunModeConfigFactory {

  private MobileRunModeConfigFactory() {
  }

  public static MobileRunModeConfig getConfig() {
    return ConfigCache.getOrCreate(MobileRunModeConfig.class);
  }
}

package config.factory;

import config.GeneralConfig;
import org.aeonbits.owner.ConfigCache;

public class GeneralConfigFactory {

  private GeneralConfigFactory() {
  }

  public static GeneralConfig getConfig() {
    return ConfigCache.getOrCreate(GeneralConfig.class);
  }
}

package config.factory;

import config.LocalConfig;
import config.SeleniumGridConfig;
import org.aeonbits.owner.ConfigCache;

public class LocalConfigFactory {
  private LocalConfigFactory() {
  }
  public static LocalConfig getConfig() {
    return ConfigCache.getOrCreate(LocalConfig.class);
  }
}

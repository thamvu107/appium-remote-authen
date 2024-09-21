package config.factory;

import config.SeleniumGridConfig;
import org.aeonbits.owner.ConfigCache;

public class SeleniumGridConfigFactory {
  private SeleniumGridConfigFactory() {
  }

  public static SeleniumGridConfig getConfig() {
    return ConfigCache.getOrCreate(SeleniumGridConfig.class);
  }
}

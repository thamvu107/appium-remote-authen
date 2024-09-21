package config;

import config.converters.StringToMobileRunModeConverter;
import enums.MobileRunModeType;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
  "system:properties",
  "system:env",
  "file:${user.dir}/src/test/resources/configure.properties"
})
public interface MobileRunModeConfig extends Config {
  @Key("mobileRunMode")
  @ConverterClass(StringToMobileRunModeConverter.class)
  MobileRunModeType getMobileRunMode();
}

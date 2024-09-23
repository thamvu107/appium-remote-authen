package config;

import config.converters.StringToMobileRunModeConverter;
import config.converters.StringToPlatformTypeConverter;
import enums.MobileRunModeType;
import enums.PlatformType;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
  "system:properties",
  "system:env",
  "file:${user.dir}/src/main/resources/configure.properties"
})
public interface GeneralConfig extends Config {
  @Key("platformType")
  @ConverterClass(StringToPlatformTypeConverter.class)
  PlatformType getPlatformType();
  @Key("mobileRunMode")
  @ConverterClass(StringToMobileRunModeConverter.class)
  MobileRunModeType getMobileRunMode();
}

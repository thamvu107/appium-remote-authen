package config;

import config.converters.MobileRunModeConverter;
import enums.MobileRunModeType;
import org.aeonbits.owner.Config;

@Config.Sources({
  "system:properties",
  "system:env",
  "file:${user.dir}/src/test/resources/configure.properties"
})
public interface MobileRunModeConfig extends Config {
  @Key("mobileRunMode")
  @ConverterClass(MobileRunModeConverter.class)
  MobileRunModeType getMobileRunMode();
}

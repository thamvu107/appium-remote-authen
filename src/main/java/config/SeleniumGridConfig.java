package config;

import config.converters.StringToURLConverter;
import org.aeonbits.owner.Config;

import java.net.URL;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
  "system:properties",
  "system:env",
  "file:${user.dir}/src/main/resources/seleniumGridRemote.properties",
  "file:${user.dir}/src/main/resources/seleniumGridAuthen.properties"
})
public interface SeleniumGridConfig extends Config {
  @Key("grid.url")
  @ConverterClass(StringToURLConverter.class)
  URL getUrl();

  @Key("grid.isAuthenticationRequired")
  boolean isAuthenticationRequired();

  @Key("grid.username")
  String getUsername();

  @Key("grid.password")
  String getPassword();

}

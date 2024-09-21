package config;

import config.converters.StringToURLConverter;
import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
  "file:${user.dir}/src/test/resources/local.properties"
})
public interface LocalConfig extends Config {

  @Key("local.url")
  @ConverterClass(StringToURLConverter.class)
  URL getUrl();
}

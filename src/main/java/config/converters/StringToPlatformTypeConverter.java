package config.converters;

import enums.PlatformType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToPlatformTypeConverter implements Converter<PlatformType> {

  @Override
  public PlatformType convert(Method method, String source) {
    return PlatformType.fromString(source);
  }
}

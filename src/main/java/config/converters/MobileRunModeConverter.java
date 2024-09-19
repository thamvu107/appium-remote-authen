package config.converters;

import enums.MobileRunModeType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class MobileRunModeConverter implements Converter<MobileRunModeType> {

  @Override
  public MobileRunModeType convert(Method method, String source) {
    return MobileRunModeType.valueOf(source.toUpperCase());
  }
}

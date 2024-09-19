package utils;

import org.openqa.selenium.By;

@FunctionalInterface
public interface SelectorFunction {
  By apply(String value);
}

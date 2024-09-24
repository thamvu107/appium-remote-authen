package utils;

import config.LocalConfig;
import config.SeleniumGridConfig;
import config.factory.LocalConfigFactory;
import config.factory.SeleniumGridConfigFactory;
import entity.ServerConfig;
import entity.authen.RemoteServerAuthentication;
import enums.MobileRunModeType;
import io.appium.java_client.AppiumClientConfig;
import org.openqa.selenium.UsernameAndPassword;

import java.net.URL;

public class AppiumClientConfigManager {
  private AppiumClientConfigManager() {
  }

  public static AppiumClientConfig getLocalConfig(LocalConfig localConfig) {

    return AppiumClientConfig.defaultConfig().baseUrl(localConfig.getUrl());
  }

  public static AppiumClientConfig getSeleniumGridConfig( SeleniumGridConfig seleniumGridConfig) {
    boolean isAuthenticationRequired = seleniumGridConfig.isAuthenticationRequired();

    return isAuthenticationRequired ? getAuthenticatedSeleniumGridConfig(seleniumGridConfig) :
      getNonAuthenticatedSeleniumGridConfig(seleniumGridConfig);
  }

    private static  AppiumClientConfig getNonAuthenticatedSeleniumGridConfig(SeleniumGridConfig seleniumGridConfig) {
    return AppiumClientConfig.defaultConfig().baseUrl(seleniumGridConfig.getUrl());
  }

  private static  AppiumClientConfig getAuthenticatedSeleniumGridConfig(SeleniumGridConfig seleniumGridConfig) {
//    RemoteServerAuthentication auth = new RemoteServerAuthenticationUtil().getRemoteServerAuthentication();

    return AppiumClientConfig.defaultConfig().baseUrl(seleniumGridConfig.getUrl())
      .authenticateAs(new UsernameAndPassword(seleniumGridConfig.getUsername(), seleniumGridConfig.getPassword()));
  }

}

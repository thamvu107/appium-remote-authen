package utils;

import entity.ServerConfig;
import entity.authen.RemoteServerAuthentication;
import io.appium.java_client.AppiumClientConfig;
import org.openqa.selenium.UsernameAndPassword;

public class AppiumClientConfigManager {
  private ServerConfig serverConfig;
  public AppiumClientConfigManager(ServerConfig serverConfig) {
    this.serverConfig = serverConfig;
  }

  public AppiumClientConfig getAppiumClientConfig() {
    boolean isAuthenticationRequired = serverConfig.isAuthenticationRequired();

    return isAuthenticationRequired ? getAppiumClientConfigAuthentication(serverConfig) :
      getAppiumClientConfigNoneAuthentication(serverConfig);
  }

  private  AppiumClientConfig getAppiumClientConfigNoneAuthentication(ServerConfig serverConfig) {
    return AppiumClientConfig.defaultConfig().baseUrl(serverConfig.getServerURL());
  }

  private  AppiumClientConfig getAppiumClientConfigAuthentication(ServerConfig serverConfig) {
    RemoteServerAuthentication auth = new RemoteServerAuthenticationUtil().getRemoteServerAuthentication();

    return AppiumClientConfig.defaultConfig().baseUrl(serverConfig.getServerURL())
      .authenticateAs(new UsernameAndPassword(auth.getUsername(), auth.getPassword()));
  }

}

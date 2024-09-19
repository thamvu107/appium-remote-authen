package utils;

import constants.filePaths.jsonFiles.ServerConfigPathConstants;
import entity.ServerConfig;
import entity.authen.RemoteServerAuthentication;
import io.appium.java_client.AppiumClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.ClientConfig;

import java.net.URL;
import java.nio.file.Path;

@Slf4j
public class ServerURLUtil {

  public static ServerConfig getServerConfig() {

    String remoteInfoViaEnvVar = System.getenv("isRemote");
    String remoteInfoViaCmdLineArgs = System.getProperty("isRemote");
    String isRemote = remoteInfoViaEnvVar != null ? remoteInfoViaEnvVar : remoteInfoViaCmdLineArgs;

    if (isRemote == null) {
      isRemote = "false";
    }

    Path serverConfigurePath  = null;
    switch (isRemote.toLowerCase()) {
      case "true":
        serverConfigurePath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_CONFIG_JSON);
        break;
      case "false":
      default:
        serverConfigurePath = Path.of(ServerConfigPathConstants.LOCAL_SERVER_CONFIG_JSON);
        break;
    }

    ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);

    return serverConfig;
  }

  public static RemoteServerAuthentication getRemoteServerAuthentication(){
   Path authenticationPath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_AUTHENTICATION_JSON);
    RemoteServerAuthentication remoteServerAuthentication = DataObjectBuilderUtil.buildDataObject(authenticationPath, RemoteServerAuthentication.class);

    return remoteServerAuthentication;

  }


//  public static URL getServerURL() {
//
//    String remoteInfoViaEnvVar = System.getenv("isRemote");
//    String remoteInfoViaCmdLineArgs = System.getProperty("isRemote");
//    String isRemote = remoteInfoViaEnvVar != null ? remoteInfoViaEnvVar : remoteInfoViaCmdLineArgs;
//
//    if (isRemote == null) {
//      isRemote = "false";
//    }
//
//    URL serverURL = null;
//    switch (isRemote.toLowerCase()) {
//      case "true":
//        serverURL = getRemoteServerURL();
//        break;
//      case "false":
//      default:
//        serverURL = getLocalServerURL();
//        break;
//    }
//    System.out.println("getServerURL: " + serverURL);
//
//    return serverURL;
//  }
//  private static URL getLocalServerURL() {
//
//    Path serverConfigurePath = Path.of(ServerConfigPathConstants.LOCAL_SERVER_CONFIG_JSON);
//    ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);
//
//    try {
//      return serverConfig.getServerURL();
//    } catch (Exception e) {
//      log.atInfo().setMessage("Invalid server URL").setCause(e).log();
//      throw new RuntimeException("Invalid server URL" + e);
//
//    }
//  }
//
//  private static URL getRemoteServerURL() {
//
//    Path serverConfigurePath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_CONFIG_JSON);
//    ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);
//
//    try {
//      return serverConfig.getServerURL();
//    } catch (Exception e) {
//      log.atInfo().setMessage("Invalid server URL").setCause(e).log();
//      throw new RuntimeException("Invalid server URL" + e);
//
//    }
//  }
//
//  public HttpCommandExecutor getRemoteServerURLAuthentication(URL serverURL, String username, String password) {
//    try {
//// Set up the ClientConfig for authentication
//      AppiumClientConfig  appiumClientConfig = AppiumClientConfig.defaultConfig()
//        .baseUrl(serverURL)  // Appium Server URL
//        .authenticateAs(new UsernameAndPassword(username, password)); // Authentication credentials
//
//      // Create the HttpCommandExecutor with clientConfig
//      return new HttpCommandExecutor(appiumClientConfig);
//
//    } catch (Exception e) {
//      throw new RuntimeException( e);
//    }
//  }
//
//  private static HttpCommandExecutor getRemoteServerURLV2() {
//
//    Path serverConfigurePath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_CONFIG_JSON);
//    ServerConfig serverConfig = DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);
//
//    try {
//      return serverConfig.getRemoteServerURLAuthenticationV2();
//    } catch (Exception e) {
//      log.atInfo().setMessage("Invalid server URL").setCause(e).log();
//      throw new RuntimeException("Invalid server URL" + e);
//
//    }
//  }
}

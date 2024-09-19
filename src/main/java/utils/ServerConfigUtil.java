package utils;

import constants.filePaths.jsonFiles.ServerConfigPathConstants;
import entity.ServerConfig;
import entity.authen.RemoteServerAuthentication;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
public class ServerConfigUtil {

  public ServerConfig getServerConfig() {

    String isRemote = getIsRemote();

    Path serverConfigurePath;
    switch (isRemote.toLowerCase()) {
      case "true":
        serverConfigurePath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_CONFIG_JSON);
        break;
      case "false":
        serverConfigurePath = Path.of(ServerConfigPathConstants.LOCAL_SERVER_CONFIG_JSON);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + isRemote);
    }

    return DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);
  }

  private String getIsRemote() {
    String envVarRemote = System.getenv("isRemote");
    String cmdLineArgsRemote = System.getProperty("isRemote");

    return envVarRemote != null ? envVarRemote : cmdLineArgsRemote;
  }
}

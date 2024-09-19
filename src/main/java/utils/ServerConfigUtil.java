package utils;

import constants.filePaths.jsonFiles.ServerConfigPathConstants;
import entity.ServerConfig;
import entity.authen.RemoteServerAuthentication;
import enums.MobileRunModeType;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
public class ServerConfigUtil {
  private MobileRunModeType mobileRunModeType;;
  public ServerConfigUtil(MobileRunModeType mobileRunModeType) {
    this.mobileRunModeType = mobileRunModeType;
  }

  public ServerConfig getServerConfig() {
    Path serverConfigurePath;
    switch (mobileRunModeType) {
      case REMOTE:
        serverConfigurePath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_CONFIG_JSON);
        break;
      case LOCAL:
        serverConfigurePath = Path.of(ServerConfigPathConstants.LOCAL_SERVER_CONFIG_JSON);
        break;
      default:
        throw new IllegalStateException("Unexpected run mode: " + mobileRunModeType);
    }

    return DataObjectBuilderUtil.buildDataObject(serverConfigurePath, ServerConfig.class);
  }

}

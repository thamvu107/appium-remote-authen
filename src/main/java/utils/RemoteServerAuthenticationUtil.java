package utils;

import constants.filePaths.jsonFiles.ServerConfigPathConstants;
import entity.authen.RemoteServerAuthentication;

import java.nio.file.Path;

public class RemoteServerAuthenticationUtil {
  public  RemoteServerAuthentication getRemoteServerAuthentication() {
    Path authenticationPath = Path.of(ServerConfigPathConstants.REMOTE_SERVER_AUTHENTICATION_JSON);

    return DataObjectBuilderUtil.buildDataObject(authenticationPath, RemoteServerAuthentication.class);
  }
}

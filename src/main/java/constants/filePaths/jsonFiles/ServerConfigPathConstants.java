package constants.filePaths.jsonFiles;

import java.io.File;

public interface ServerConfigPathConstants extends JsonFileConstants {
    String LOCAL_SERVER_CONFIG_JSON = JSON_BASE_PATH + "serverConfigure" + File.separator + "LocalServer" + JSON_SUFFIX;
    String REMOTE_SERVER_CONFIG_JSON = JSON_BASE_PATH + "serverConfigure" + File.separator + "RemoteServer" + JSON_SUFFIX;
    String REMOTE_SERVER_AUTHENTICATION_JSON = JSON_BASE_PATH + "serverConfigure" + File.separator + "Authentication" + JSON_SUFFIX;
}


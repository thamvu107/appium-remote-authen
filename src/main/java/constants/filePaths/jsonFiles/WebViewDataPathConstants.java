package constants.filePaths.jsonFiles;

import java.io.File;

public interface WebViewDataPathConstants extends JsonFileConstants {
    String WEB_VIEW_PATH = "webView" + File.separator;
    String MENU_ITEM_JSON = JSON_BASE_PATH + WEB_VIEW_PATH + "MainMenuItems" + JSON_SUFFIX;

}

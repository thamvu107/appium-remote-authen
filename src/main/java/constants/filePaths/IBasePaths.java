package constants.filePaths;

import java.io.File;

public interface IBasePaths {
    String USER_DIR_PATH = System.getProperty("user.dir") + File.separator;
    String TEST_RESOURCES_PATH = USER_DIR_PATH + "src" + File.separator + "test"
            + File.separator + "resources" + File.separator;
}

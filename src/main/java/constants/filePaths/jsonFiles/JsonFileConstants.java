package constants.filePaths.jsonFiles;

import constants.filePaths.IBasePaths;

import java.io.File;

public interface JsonFileConstants extends IBasePaths {
    String JSON_BASE_PATH = TEST_RESOURCES_PATH + "testData" + File.separator + "jsonFiles" + File.separator;
    String JSON_SUFFIX = ".json";
}

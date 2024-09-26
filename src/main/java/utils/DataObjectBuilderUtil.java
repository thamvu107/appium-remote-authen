package utils;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class DataObjectBuilderUtil {
  private static final Gson gson = new Gson();

  public static <T> T buildDataObject(@NonNull Path relativeFilePath, @NonNull Class<T> dataType) {
    Path absoluteFilePath = new PathUtil(relativeFilePath).getAbsolutePath();

    try (Reader reader = getReader(absoluteFilePath, relativeFilePath)) {
      return new Gson().fromJson(reader, dataType);
    } catch (Exception e) {
      String errorMessage = String.format("[ERR] Error reading file: %s", absoluteFilePath);
      log.atError().setMessage(errorMessage).setCause(e).log();
      throw new RuntimeException(errorMessage, e);
    }
}

public static <T> List<T> buildDataObjectList(@NonNull Path relativeFilePath, @NonNull Class<T> dataType) {
  Path absoluteFilePath = new PathUtil(relativeFilePath).getAbsolutePath();

  try (Reader reader = Files.newBufferedReader(absoluteFilePath)) {
    Type listType = TypeToken.getParameterized(List.class, dataType).getType();
    return gson.fromJson(reader, listType);
  } catch (NoSuchFileException e) {
    log.error("[ERR] Could not find the file: {}", absoluteFilePath, e);
    log.error("Please check that the file exists and is readable.", e);
    throw new RuntimeException(String.format("[ERR] Could not find the file: %s", absoluteFilePath), e);
  } catch (JsonSyntaxException e) {
    log.error("[ERR] JSON syntax error in file: {}", absoluteFilePath, e);
    throw new RuntimeException(String.format("[ERR] JSON syntax error in file: %s", absoluteFilePath), e);
  } catch (IOException e) {
    log.error("[ERR] IO error reading file: {}", absoluteFilePath, e);
    throw new RuntimeException(String.format("[ERR] IO error reading file: %s", absoluteFilePath), e);
  }
}

  private static Reader getReader(Path absoluteFilePath, Path relativeFilePath) throws IOException {
    if (Files.exists(absoluteFilePath)) {
      return Files.newBufferedReader(absoluteFilePath);
    } else {
      InputStream inputStream = DataObjectBuilderUtil.class.getResourceAsStream(relativeFilePath.toString());
      if (inputStream == null) {
        throw new FileNotFoundException("File not found in resources: " + relativeFilePath.toString());
      }
      return new InputStreamReader(inputStream);
    }
  }
}

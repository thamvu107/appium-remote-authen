package utils;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
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
    Reader reader;
//    String absoluteFilePath = new PathUtil(relativeFilePath).getAbsolutePath();

    Path absoluteFilePath = new PathUtil(relativeFilePath).getAbsolutePath();
    if (Files.exists(absoluteFilePath)) {
      try {
        reader = Files.newBufferedReader(absoluteFilePath);
      } catch (Exception e) {
        log.atError().setMessage(String.format("[ERR] Exception error reading file: %s", absoluteFilePath)).setCause(e).log();
        throw new RuntimeException(String.format("[ERR] Exception error reading file: %s", absoluteFilePath), e);
      }
    } else {
      try {
        InputStream inputStream = DataObjectBuilderUtil.class.getResourceAsStream(relativeFilePath.toString());
        reader = new InputStreamReader(inputStream);
      } catch (Exception e) {
        log.atError().setMessage("ERR error while reading file from resource: " + e.getMessage()).setCause(e).log();
        throw new RuntimeException("ERR error while reading file from resource: " + e.getMessage());
      }
    }

    return new Gson().fromJson(reader, dataType);
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


}

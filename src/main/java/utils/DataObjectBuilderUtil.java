package utils;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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

    try (Reader reader = Files.newBufferedReader(absoluteFilePath)) {
      return gson.fromJson(reader, dataType);
    } catch (NoSuchFileException e) {
      log.atError().setMessage(String.format("[ERR] Could not find the file: %s", absoluteFilePath)).setCause(e).log();
      log.atError().setMessage("Please check that the file exists and is readable.").setCause(e).log();
      throw new RuntimeException(String.format("[ERR] Could not find the file: %s", absoluteFilePath), e);
    } catch (JsonSyntaxException e) {
      log.atError().setMessage(String.format("[ERR] JSON syntax error in file: %s", absoluteFilePath)).setCause(e).log();
      throw new RuntimeException(String.format("[ERR] JSON syntax error in file: %s", absoluteFilePath), e);
    } catch (IOException e) {
      log.atError().setMessage(String.format("[ERR] IO error reading file: %s", absoluteFilePath)).setCause(e).log();
      throw new RuntimeException(String.format("[ERR] IO error reading file: %s", absoluteFilePath), e);
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


}

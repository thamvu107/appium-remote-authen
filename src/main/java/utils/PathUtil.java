package utils;

import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public class PathUtil {

    private final Path rootPath;
    private final Path relativePath;

    public PathUtil(Path relativePath) {
        this.relativePath = relativePath;
        this.rootPath = Paths.get(System.getProperty("user.dir"));
    }

    public Path getAbsolutePath() {

        return relativePath.isAbsolute() ? relativePath : rootPath.resolve(relativePath).toAbsolutePath();
    }

    public String getAbsolutePathString() {
        return getAbsolutePath().toString();
    }
}

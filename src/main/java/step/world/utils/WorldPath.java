package step.world.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * enum for storage paths to world presets directories
 */
public enum WorldPath {
    PRESETS("worlds/presets"),
    ISLANDS("worlds/islands"),
    DUNGEONS("worlds/dungeons"),
    MAIN_LOBBY("worlds/presets/mainLobby"),
    ISLAND_PRESET("worlds/presets/island");

    private final String path;


    WorldPath(String path) {
        this.path = path;
    }

    public Path getPath() {
        return Paths.get(path);
    }
    public Path getPath(String... more) {
        return Paths.get(path, more);
    }

    public String getPathString() {
        return path;
    }
}

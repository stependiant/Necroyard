package step.world.generator;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.anvil.AnvilLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import step.world.utils.WorldPath;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;

public class PlayerIslandGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerIslandGenerator.class);

    public static InstanceContainer loadIsland(UUID playerId) {
        Path islandFile = WorldPath.ISLANDS.getPath(playerId + ".island");

        if (Files.exists(islandFile)) {
            return loadOldIsland(islandFile);
        } else {
            return createNewIsland(playerId);
        }
    }

    private static InstanceContainer createNewIsland(UUID playerId) {
        Path presetIslandPath = WorldPath.ISLAND_PRESET.getPath();
        Path newIslandPath = WorldPath.ISLANDS.getPath(playerId + ".island");

        if (Files.exists(presetIslandPath)) {
            try {
                Files.createDirectory(newIslandPath);
                copyDirectory(presetIslandPath, newIslandPath);
            } catch (IOException e) {
                LOGGER.debug("Cant create or copy island in directory");
            }
        } else {
            LOGGER.debug("preset island didnt exist");
        }

        AnvilLoader anvilLoader = new AnvilLoader(newIslandPath);

        return MinecraftServer.getInstanceManager().createInstanceContainer(anvilLoader);
    }
    private static void copyDirectory(Path source, Path target) throws IOException {
        Files.walkFileTree(source, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetDir = target.resolve(source.relativize(dir));
                Files.createDirectories(targetDir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, target.resolve(source.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static InstanceContainer loadOldIsland(Path path) {
        AnvilLoader anvilLoader = new AnvilLoader(path);
        return MinecraftServer.getInstanceManager().createInstanceContainer(anvilLoader);
    }




}

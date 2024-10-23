package step.world.generator;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.anvil.AnvilLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class PlayerIslandGenerator {

    public static InstanceContainer loadIsland(UUID playerId) {
        Path islandFIle = Path.of("src\\main\\resources\\islands\\" + playerId + ".island");

        if (Files.exists(islandFIle)) {
            return loadOldIsland(islandFIle);
        } else {
            return createNewIsland();
        }
    }

    private static InstanceContainer createNewIsland() {
        // TODO: load island from AnvilLoader and save in src\main\resources\islands\
        return null;
    }

    private static InstanceContainer loadOldIsland(Path path) {
        AnvilLoader anvilLoader = new AnvilLoader(path);
        return MinecraftServer.getInstanceManager().createInstanceContainer(anvilLoader);
    }




}
